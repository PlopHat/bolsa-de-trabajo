package cl.utem.bolsadetrabajo_backend.domain.exception.types;

public class AuthorizationException extends RuntimeException {
  public AuthorizationException() {
    super("user is not authorized");
  }
  public AuthorizationException(String message) {
    super(message);
  }
}
