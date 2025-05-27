package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
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
  private String email;
  private String password;
  private String name;
  private String lastname;
  private UtemRoles role;
}