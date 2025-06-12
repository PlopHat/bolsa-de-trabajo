package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRegisterRequest {
  @JsonProperty(value = "email", required = true)
  @Email(message = "invalid email format")
  @NotNull(message = "email is required")
  private String email;

  @JsonProperty(value = "password", required = true)
  @NotNull(message = "password is required")
  private String password;

  @JsonProperty(value = "name")
  @NotNull(message = "name must not be null")
  private String name;

  @JsonProperty(value = "lastName")
  private String lastName;

}
