package cl.utem.bolsadetrabajo_backend.domain.exception.types;

public class ValidationException extends RuntimeException {
  public ValidationException() {
    super("object not found or not valid");
  }
  public ValidationException(String message) {
    super(message);
  }
}
