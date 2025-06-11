package cl.utem.bolsadetrabajo_backend.domain.exception;

import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFoundException(WebRequest req, Exception ex) {
    log.error("Error capturado <ValidationException> : ", ex);
    ProblemDetail problemDetail = makeWebRequestProblemDetail(req, HttpStatus.UNPROCESSABLE_ENTITY, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleGlobalException(WebRequest req, Exception ex) {
    log.error("Error capturado <Exception> : ", ex);
    ProblemDetail problemDetail = makeWebRequestProblemDetail(req, HttpStatus.INTERNAL_SERVER_ERROR, ex);
    return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
  }

  private static ProblemDetail makeWebRequestProblemDetail(WebRequest req,
                                                           HttpStatus status, Exception ex) {
    log.error("Generando problem detail WebRequest para : {}", ex.getMessage());
    final URI type = URI.create("https://http.cat/" + status.value());
    final String detail = ex.getMessage();
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
    problemDetail.setType(type);
    problemDetail.setTitle(status.getReasonPhrase());
    if (req != null) {
      final URI instance = URI.create(req.getDescription(false));
      problemDetail.setInstance(instance);
    }
    return problemDetail;
  }

  private static ProblemDetail makeServletRequestProblemDetail(HttpServletRequest req,
                                                               HttpStatus status, Exception ex) {
    log.error("Generando problem detail ServletRequest para : {}", ex.getMessage());
    final URI type = URI.create("https://http.cat/" + status.value());
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

  private ProblemDetail handleValidationException(MethodArgumentNotValidException ex,
                                                  HttpStatus status, WebRequest req) {
    log.error("Generando problem detail ValidationException para : {}", ex.getMessage());
    final URI type = URI.create("https://http.cat/" + status.value());
    final String detail = "Error de validación";
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
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
    return problemDetail;
  }

}
