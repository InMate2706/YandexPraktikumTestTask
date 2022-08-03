import constants.DeliveryParamConstants;
import enums.CargoType;
import exceptions.DeliveryParamBadRequestException;
import exceptions.NotSupportDeliveryTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static constants.DeliveryParamConstants.MIN_DELIVERY_PRICE;
import static constants.DeliveryParamValidatorConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Проверка валидации параметров функции")
class CalculateDeliveryPriceTest {

    private final Random random = new Random();

    @Test
    @DisplayName("Получение стоимости доставки с ближайшим отрицательным значением дистанции, вызывает иключение")
    void getDeliveryPriceWithNearestNegativeDistanceValue() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(-Double.MIN_VALUE, CargoType.getRandomCargoType(), random.nextBoolean(),
                        DeliveryParamConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_VALID_PARAM_DISTANCE_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки со значением дистанции равным 0, вызывает иключение")
    void getDeliveryPriceWithZeroDistanceValue() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(0d, CargoType.getRandomCargoType(), random.nextBoolean(),
                        DeliveryParamConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_VALID_PARAM_DISTANCE_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки с несуществующим значением коэффициента загруженности службы доставки, вызывает иключение")
    void getDeliveryPriceWithNonExistsWorkloadDeliveryCoefficient() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(1d, CargoType.getRandomCargoType(), random.nextBoolean(), Math.nextUp(1f)));

        assertEquals(NOT_VALID_PARAM_WORKLOAD_COEFFICIENT_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки со значением дистанции выше допустимого для хрупких грузов, вызывает иключение")
    void getDeliveryPriceWithNotLimitDistanceForFragileCargo() {
        NotSupportDeliveryTypeException throwsException = assertThrows(NotSupportDeliveryTypeException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(Math.nextUp(30d), CargoType.getRandomCargoType(), true,
                        DeliveryParamConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_SUPPORT_DELIVERY_TYPE_FOR_FRAGILE_CARGO_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение минимальной стоимости доставки, так как сумма доставки по входным параметрам ниже минимальной")
    void getDeliveryPriceReturnMinPrice() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(2d, CargoType.LITTLE, false,
                DeliveryParamConstants.DEFAULT_WORKLOAD_COEFFICIENT);

        assertEquals(MIN_DELIVERY_PRICE, result);
    }

    @Test
    @DisplayName("Позитивный сценарий получения стоимости доставки, для хрупкого груза с максимальным значением растояния")
    void getDeliveryPriceWithMaxDistanceForFragileCargo() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(Math.nextDown(30d), CargoType.BIG, true,
                DeliveryParamConstants.VERY_HIGH_WORKLOAD_COEFFICIENT);

        assertEquals(1120f, result);
    }

    @Test
    @DisplayName("Позитивный сценарий получения стоимости доставки, набор данных №1")
    void getDeliveryPricePositiveKeys_1() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(100.1d, CargoType.BIG, false,
                DeliveryParamConstants.HIGH_WORKLOAD_COEFFICIENT);

        assertEquals(700f, result);
    }

    @Test
    @DisplayName("Позитивный сценарий получения стоимости доставки, набор данных №2")
    void getDeliveryPricePositiveKeys_2() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(5.9d, CargoType.LITTLE, true,
                DeliveryParamConstants.INCREASED_WORKLOAD_COEFFICIENT);

        assertEquals(600f, result);
    }
}