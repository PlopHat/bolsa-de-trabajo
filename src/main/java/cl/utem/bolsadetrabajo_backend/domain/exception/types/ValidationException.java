package cl.utem.bolsadetrabajo_backend.domain.exception.types;

public class ValidationException extends RuntimeException {
  public ValidationException() {
    super("");
  }
  public ValidationException(String message) {
    super(message);
  }
}
