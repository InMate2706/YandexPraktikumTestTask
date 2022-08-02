package constants;

import static constants.WorkloadDeliveryCoefficientConstants.ALLOWABLE_WORKLOAD_COEFFICIENT;

/**
 * Класс констант для валидации входных параметров функции
 */
public class DeliveryParamValidatorConstants {

    public final static String NOT_VALID_PARAM_DISTANCE_MESSAGE = "Значение параметра дистанция не можеть быть меньше или равно 0";
    public final static String NOT_VALID_PARAM_WORKLOAD_COEFFICIENT_MESSAGE = String.format("Коэффициент загрузки должен быть " +
            "равен одному из допустимых значений: %s", ALLOWABLE_WORKLOAD_COEFFICIENT);
    public final static String NOT_SUPPORT_DELIVERY_TYPE_FOR_FRAGILE_CARGO_MESSAGE = "Хрупкие грузы нельзя возить на расстояние более 30 км";
    public final static Double MIN_VALUE_DISTANCE = Double.MIN_VALUE;
}