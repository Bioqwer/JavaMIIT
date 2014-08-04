package lab3;

/**
 * Created by Antony on 04.08.2014.
 */
public class IncompatibleVectorSizesException extends Exception {

    public IncompatibleVectorSizesException() {
    }

    public IncompatibleVectorSizesException(String message) {
        super(message);
    }

    public IncompatibleVectorSizesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleVectorSizesException(Throwable cause) {
        super(cause);
    }

    public IncompatibleVectorSizesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
