package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Getter
@Setter
@Entity
@Table(name = "utem_users")
public class UtemUser extends BaseEntity {
  @Column(name = "email", nullable = false, unique = true)
  @Email(message = "email address is not valid")
  private String email;

  @Column(name = "password", nullable = false)
  @NotNull(message = "password can't be null")
  private String password;

  @Column(name = "name", nullable = false)
  @NotNull(message = "name can't be null")
  private String name;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private UtemRoles role;

}
