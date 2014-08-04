package lab3;

/**
 * Created by Antony on 04.08.2014.
 */
public class VectorIndexOutOfBoundsException extends RuntimeException {

    public VectorIndexOutOfBoundsException() {
    }

    public VectorIndexOutOfBoundsException(String message) {
        super(message);
    }

    public VectorIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public VectorIndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    public VectorIndexOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
