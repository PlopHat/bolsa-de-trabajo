package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
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

  @JsonProperty(value = "name")
  @Schema(
          description = "Nombre del usuario",
          example = "Juan",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String name;

  @JsonProperty(value = "lastName")
  @Schema(
          description = "Apellido del usuario",
          example = "Pérez",
          requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String lastName;

  @JsonProperty(value = "companyRut")
  @Schema(
          description = "Rut de la empresa asociada al usuario",
          example = "99999999-9",
          requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private String companyRut;

  @JsonProperty(value = "role")
  @Schema(
          description = "Rol del usuario",
          example = "ROLE_USER",
          requiredMode = Schema.RequiredMode.NOT_REQUIRED,
          allowableValues = {"ROLE_USER", "ROLE_ADMINISTRATOR", "ROLE_COMPANY"}
  )
  private UtemRoles role;
}
