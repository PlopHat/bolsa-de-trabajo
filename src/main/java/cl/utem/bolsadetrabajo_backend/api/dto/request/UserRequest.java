package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty(value = "email")
  private String email;

  @JsonProperty(value = "password")
  private String password;

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "lastName")
  private String lastName;

  @JsonProperty(value = "role")
  private UtemRoles role;
}
