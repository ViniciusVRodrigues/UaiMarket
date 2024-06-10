package exception;

public class IncorrectCredentialsException extends Exception {
    public IncorrectCredentialsException() {
        super();
    }

    // Construtor que recebe uma mensagem de erro
    public IncorrectCredentialsException(String message) {
        super(message);
    }

    public IncorrectCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCredentialsException(Throwable cause) {
        super(cause);
    }
}
