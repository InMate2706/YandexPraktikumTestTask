import constants.WorkloadDeliveryCoefficientConstants;
import enums.CargoType;
import exceptions.DeliveryParamBadRequestException;
import exceptions.NotSupportDeliveryTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static constants.DeliveryParamValidatorConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка валидации параметров функции")
class CalculateDeliveryPriceValidationTest {

    private final Random random = new Random();

    @Test
    @DisplayName("Получение стоимости доставки с отрицательнм значением дистанции, вызывает иключение")
    void getDeliveryPriceWithNegativeDistanceValue() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(-Double.MIN_VALUE, CargoType.getRandomCargoType() , random.nextBoolean(),
                        WorkloadDeliveryCoefficientConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_VALID_PARAM_DISTANCE_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки со значением дистанции равным 0, вызывает иключение")
    void getDeliveryPriceWithZeroDistanceValue() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(0d, CargoType.getRandomCargoType() , random.nextBoolean(),
                        WorkloadDeliveryCoefficientConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_VALID_PARAM_DISTANCE_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки с несуществующим значением коэффициента загруженности службы доставки, вызывает иключение")
    void getDeliveryPriceWithNonExistsWorkloadDeliveryCoefficient() {
        DeliveryParamBadRequestException throwsException = assertThrows(DeliveryParamBadRequestException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(1d, CargoType.getRandomCargoType() , random.nextBoolean(), 1.1f));

        assertEquals(NOT_VALID_PARAM_WORKLOAD_COEFFICIENT_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение стоимости доставки со значением дистанции выше допустимого для хрупких грузов, вызывает иключение")
    void getDeliveryPriceWithNotLimitDistanceForFragileCargo() {
        NotSupportDeliveryTypeException throwsException = assertThrows(NotSupportDeliveryTypeException.class,
                () -> YandexPractikumTestTaskMain.calculateDeliveryPrice(Math.nextUp(30d), CargoType.getRandomCargoType() , true,
                        WorkloadDeliveryCoefficientConstants.getRandomWorkLoadCoefficient()));

        assertEquals(NOT_SUPPORT_DELIVERY_TYPE_FOR_FRAGILE_CARGO_MESSAGE, throwsException.getMessage());
    }

    @Test
    @DisplayName("Получение минимальной стоимости доставки, так как сумма доставки по входным параметрам ниже минимальной")
    void getDeliveryPriceReturnMinPrice() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(3d, CargoType.LITTLE, false,
                WorkloadDeliveryCoefficientConstants.DEFAULT_WORKLOAD_COEFFICIENT);

        assertEquals(400f, result);
    }

    @Test
    @DisplayName("Позитивный сценарий получения стоимости доставки")
    void getDeliveryPrice() {
        float result = YandexPractikumTestTaskMain.calculateDeliveryPrice(29d, CargoType.BIG, true,
                WorkloadDeliveryCoefficientConstants.VERY_HIGH_WORKLOAD_COEFFICIENT);

        assertEquals(1120f, result);
    }
}