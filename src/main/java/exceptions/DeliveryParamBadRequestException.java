package exceptions;

public class DeliveryParamBadRequestException extends RuntimeException {

    public DeliveryParamBadRequestException(String message) {
        super(message);
    }
}