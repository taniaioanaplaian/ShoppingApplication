package exception;
public class ReaderException extends Exception {

    private static final long serialVersionUID = 1L;
    /**
     * Creates ReaderException
     * @param message to be printed if exception caught
     */
    public ReaderException(String message) {
        super(message);
    }

}