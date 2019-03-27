package ckmbks.framework.exceptions;

public class AlertException extends RuntimeException {
    public AlertException(String message) {
        super(message, null, false, false);
    }
}
