package exception;

public class XMLParsingException extends Exception {

    public XMLParsingException(String message) {
        super(message);
    }

    public XMLParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLParsingException(Throwable cause) {
        super(cause);
    }
}
