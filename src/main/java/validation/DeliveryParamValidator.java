package validation;

import exceptions.DeliveryParamBadRequestException;
import exceptions.NotSupportDeliveryTypeException;

import static constants.DeliveryParamConstants.ALLOWABLE_WORKLOAD_COEFFICIENT;
import static constants.DeliveryParamValidatorConstants.*;

/**
 * Класс валидации входных параметров рассчета доставки
 */
public class DeliveryParamValidator {

    /**
     * Метод валидирует входные параметры для метода calculateDeliveryPrice
     *
     * @param distance              расстояние доставки
     * @param isFragileCargo        является ли груз хрупким
     * @param workloadDeliveryLevel коэффициент загруженности службы доставки
     * @throws DeliveryParamBadRequestException если расстояние или коэффициент загруженности службы доставки указаны неверно
     * @throws NotSupportDeliveryTypeException если указано расстоние выше допустимого для хрупких грузов
     */
    public static void validateCorrectDeliveryParam(Double distance, boolean isFragileCargo, Float workloadDeliveryLevel) {
        if (distance <= MIN_VALUE_DISTANCE) {
            throw new DeliveryParamBadRequestException(NOT_VALID_PARAM_DISTANCE_MESSAGE);
        } else if (!ALLOWABLE_WORKLOAD_COEFFICIENT.contains(workloadDeliveryLevel)) {
            throw new DeliveryParamBadRequestException(NOT_VALID_PARAM_WORKLOAD_COEFFICIENT_MESSAGE);
        } else if (isFragileCargo && (distance > 30d)) {
            throw new NotSupportDeliveryTypeException(NOT_SUPPORT_DELIVERY_TYPE_FOR_FRAGILE_CARGO_MESSAGE);
        }
    }
}