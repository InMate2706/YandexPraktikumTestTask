package exceptions;

public class NotSupportDeliveryTypeException extends RuntimeException {

    public NotSupportDeliveryTypeException(String message) {
        super(message);
    }
}