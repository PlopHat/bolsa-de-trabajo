package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser}
 */
@Data
public class UserResponse implements Serializable {
  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  @JsonProperty(value = "email")
  String email;

  @JsonProperty(value = "name")
  String name;

  @JsonProperty(value = "lastName")
  String lastName;

  @JsonProperty(value = "role")
  UtemRoles role;

  public UserResponse toDto(UtemUser user) {
    this.id = user.getId();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
    this.email = user.getEmail();
    this.name = user.getName();
    this.lastName = user.getLastname();
    this.role = user.getRole();
    return this;

  }

}
