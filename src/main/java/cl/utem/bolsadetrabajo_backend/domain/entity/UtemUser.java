package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "utem_users")
public class UtemUser extends BaseEntity {
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UtemRoles role;

}