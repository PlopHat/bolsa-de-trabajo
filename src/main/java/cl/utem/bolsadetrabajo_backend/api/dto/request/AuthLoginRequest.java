package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthLoginRequest {
  @JsonProperty(value = "email", required = true)
  @Email(message = "invalid email format")
  @NotNull(message = "email is required")
  @Schema(
          description = "Email del usuario",
          example = "hola@utem.cl"
  )
  private String email;

  @JsonProperty(value = "password", required = true)
  @NotNull(message = "password is required")
  @Schema(
          description = "Contraseña del usuario",
          example = "password123",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String password;

}
