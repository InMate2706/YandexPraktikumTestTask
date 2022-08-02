import enums.CargoType;

import static constants.DeliveryParamConstants.MIN_DELIVERY_PRICE;
import static validation.DeliveryParamValidator.validateCorrectDeliveryParam;

public class YandexPractikumTestTaskMain {

    public static void main(String[] args) {
    }

    /**
     * Метод для рассчета стоимости доставки
     *
     * @param distance                    расстояние доставки
     * @param cargoType                   тип груза
     * @param isFragileCargo              является ли груз хрупким
     * @param workloadDeliveryCoefficient коэффициент загруженности службы доставки
     * @return цена доставки
     */
    public static float calculateDeliveryPrice(Double distance, CargoType cargoType, boolean isFragileCargo, Float workloadDeliveryCoefficient) {
        validateCorrectDeliveryParam(distance, isFragileCargo, workloadDeliveryCoefficient);
        float resultPrice = (calculateDelivyPriceByDistance(distance) + calculateDelivyPriceByCargoType(cargoType)
                + calculateDelivyPriceByFragileCargo(isFragileCargo)) * workloadDeliveryCoefficient;
        return Math.max(resultPrice, MIN_DELIVERY_PRICE);
    }

    /**
     * Метод для рассчета цены доставки с учетом расстояния
     *
     * @param distance расстояние
     * @return цена доставки
     */
    public static float calculateDelivyPriceByDistance(Double distance) {
        if (distance > 30d) {
            return 300f;
        } else if (distance <= 2d) {
            return 50f;
        } else if (distance <= 10d) {
            return 100f;
        } else {
            return 200f;
        }
    }

    /**
     * Метод для рассчета цены доставки с учетом типа груза
     *
     * @param cargoType тип груза {@link CargoType}
     * @return цена доставки
     */
    public static float calculateDelivyPriceByCargoType(CargoType cargoType) {
        return switch (cargoType) {
            case LITTLE -> 100f;
            case BIG -> 200f;
        };
    }

    /**
     * Метод для рассчета цены доставки с учетом типа груза
     *
     * @param isFragileCargo является ли груз хрупким
     * @return цена доставки
     */
    public static float calculateDelivyPriceByFragileCargo(boolean isFragileCargo) {
        return isFragileCargo ? 300f : 0f;
    }
}