package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser}
 */
@Value
public class UserResponse implements Serializable {
  Long id;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  String email;
  String name;
  String lastname;
  UtemRoles role;
}