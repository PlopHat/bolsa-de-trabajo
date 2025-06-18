package cl.utem.bolsadetrabajo_backend.domain.exception.types;

public class CustomEntityNotFoundException extends RuntimeException {
  public CustomEntityNotFoundException() {
    super("Entity not found");
  }
  public CustomEntityNotFoundException(String message) {
    super(message);
  }
  public CustomEntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
