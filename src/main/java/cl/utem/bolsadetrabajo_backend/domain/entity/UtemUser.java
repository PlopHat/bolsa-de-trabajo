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
@Table(name = "app_users")
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

  @Column(name = "rut", nullable = false, unique = true, length = 8)
  private int rut;

  @Column(name = "digito_verificador", nullable = false, length = 1)
  private String DV;

  public void setRut(int rut) {
    this.rut = rut;
    this.DV = calculateDV(rut);
  }

  // calculate the digito verificador for a given rut
  private String calculateDV(int rut) {
    int sum = 0;
    int factor = 2;
    while (rut > 0) {
      sum += (rut % 10) * factor;
      rut /= 10;
      factor = (factor == 7) ? 2 : factor + 1;
    }
    int remainder = sum % 11;
    return (remainder == 0) ? "0" : (remainder == 1) ? "K" : String.valueOf(11 - remainder);
  }

  @ManyToOne
  @JoinColumn(name = "associated_company", nullable = true)
  private Company company;

}
