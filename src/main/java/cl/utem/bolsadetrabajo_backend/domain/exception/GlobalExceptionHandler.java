package cl.utem.bolsadetrabajo_backend.domain.exception;

import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  
  private static final String URL_DETAILS = "https://http.cat/";

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFoundException(HttpServletRequest req, ValidationException ex) {
    log.error("Error capturado <ValidationException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.UNPROCESSABLE_ENTITY, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  public ResponseEntity<ProblemDetail> handleAuthorizationDeniedException(HttpServletRequest req, AuthorizationDeniedException ex) {
    log.error("Error capturado <AuthorizationDeniedException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.UNAUTHORIZED, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(HttpServletRequest req, UsernameNotFoundException ex) {
    log.error("Error capturado <UsernameNotFoundException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.BAD_REQUEST, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleGlobalException(HttpServletRequest req, Exception ex) {
    log.error("Error capturado <Exception> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(CustomEntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleCustomEntityNotFoundException(HttpServletRequest req, CustomEntityNotFoundException ex) {
    log.error("Error capturado <CustomEntityNotFoundException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.BAD_REQUEST, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ProblemDetail> handleBadCredentialsException(HttpServletRequest req, BadCredentialsException ex) {
    log.error("Error capturado <BadCredentialsException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.BAD_REQUEST, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(InsufficientAuthenticationException.class)
  public ResponseEntity<ProblemDetail> handleInsufficientAuthenticationException(HttpServletRequest req, InsufficientAuthenticationException ex) {
    log.error("Error capturado <InsufficientAuthenticationException> : ", ex);
    ProblemDetail problemDetail = makeServletRequestProblemDetail(req, HttpStatus.UNAUTHORIZED, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ProblemDetail> handleDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException ex) {
    log.error("Error capturado <DataIntegrityViolationException> : ", ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    final URI type = URI.create(URL_DETAILS + status.value());
    ProblemDetail problemDetail = ProblemDetail.forStatus(status);
    problemDetail.setType(type);
    problemDetail.setTitle(status.getReasonPhrase());
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  private static ProblemDetail makeServletRequestProblemDetail(HttpServletRequest req,
                                                               HttpStatus status, Exception ex) {
    log.error("Generando problem detail <ServletRequest> para : {}", ex.getMessage());
    final URI type = URI.create(URL_DETAILS + status.value());
    final String detail = ex.getMessage();
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
    problemDetail.setType(type);
    problemDetail.setTitle(status.getReasonPhrase());
    if (req != null) {
      String requestUrl = req.getRequestURL().toString();
      if (req.getQueryString() != null) {
        requestUrl += "?" + req.getQueryString();
      }
      final URI instance = URI.create(requestUrl);
      problemDetail.setInstance(instance);
    }
    return problemDetail;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(WebRequest req, MethodArgumentNotValidException ex) {
    log.error("Error capturado <MethodArgumentNotValidException> : ", ex);
    HttpStatus status = HttpStatus.BAD_REQUEST;
    final URI type = URI.create(URL_DETAILS + status.value());
    final String details = ex.getMessage();
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, details);
    problemDetail.setType(type);
    problemDetail.setTitle(status.getReasonPhrase());

    if (req != null) {
      final URI instance = URI.create(req.getDescription(false));
      problemDetail.setInstance(instance);
    }
    Map<String, String> errorsMessages = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(
            objectError -> {
              String fieldName = ((FieldError) objectError).getField();
              String errorMessage = objectError.getDefaultMessage();
              errorsMessages.put(fieldName, errorMessage);
            }
    );
    problemDetail.setProperty("errors", errorsMessages);

    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

}
