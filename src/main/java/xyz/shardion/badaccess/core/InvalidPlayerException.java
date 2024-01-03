package xyz.shardion.badaccess.core;

public class InvalidPlayerException extends Exception {
    public InvalidPlayerException() {
    }

    public InvalidPlayerException(String message) {
        super(message);
    }

    public InvalidPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlayerException(Throwable cause) {
        super(cause);
    }

    public InvalidPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
