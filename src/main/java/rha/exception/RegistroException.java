package rha.exception;

public class RegistroException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public RegistroException(String message) {
        super(message);
    }
}
