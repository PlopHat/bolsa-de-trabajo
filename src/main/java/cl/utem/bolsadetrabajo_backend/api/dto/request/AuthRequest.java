package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
  @JsonProperty(value = "email", required = true)
  @Email(message = "invalid email format")
  @NotNull(message = "email is required")
  private String email;

  @JsonProperty(value = "password", required = true)
  @NotNull(message = "password is required")
  private String password;

}
