package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.List;

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

  @OneToMany(mappedBy = "offerAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Offer> offers;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OfferApplication> applications;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Curriculum> curriculums;

  @Column(name = "rut", nullable = false, unique = true, length = 10)
  private String rut;

  // Setter con validación y formateo
  public void setRut(String rut) {
    if (rut == null) {
      this.rut = null;
      return;
    }

    String limpio = rut.replace(".", "").toUpperCase();
    if (!esRutValido(limpio)) {
      throw new ValidationException("RUT no válido: " + rut);
    }

    this.rut = formatearRut(limpio);
  }

  @PrePersist
  @PreUpdate
  private void validarRut() {
    if (!esRutValido(this.rut)) {
      throw new ValidationException("RUT no válido: " + this.rut);
    }
  }

  private boolean esRutValido(String rutCompleto) {
    if (rutCompleto == null) return false;

    String sinPuntos = rutCompleto.replace(".", "").toUpperCase();
    if (!sinPuntos.matches("^\\d{7,8}-[\\dK]$")) return false;

    String[] partes = sinPuntos.split("-");
    int cuerpo;
    try {
      cuerpo = Integer.parseInt(partes[0]);
    } catch (NumberFormatException e) {
      return false;
    }

    char dv = partes[1].charAt(0);
    return calcularDV(cuerpo) == dv;
  }

  private char calcularDV(int rut) {
    int suma = 0;
    int multiplicador = 2;
    while (rut > 0) {
      suma += (rut % 10) * multiplicador;
      rut /= 10;
      multiplicador = multiplicador == 7 ? 2 : multiplicador + 1;
    }
    int resto = 11 - (suma % 11);
    if (resto == 11) return '0';
    if (resto == 10) return 'K';
    return (char) ('0' + resto);
  }

  private String formatearRut(String rutSinPuntos) {
    String[] partes = rutSinPuntos.split("-");
    String cuerpo = partes[0];
    String dv = partes[1];

    StringBuilder formateado = new StringBuilder();
    int len = cuerpo.length();
    int contador = 0;

    for (int i = len - 1; i >= 0; i--) {
      formateado.insert(0, cuerpo.charAt(i));
      contador++;
      if (contador == 3 && i != 0) {
        formateado.insert(0, ".");
        contador = 0;
      }
    }

    return formateado + "-" + dv;
  }

  @ManyToOne
  @JoinColumn(name = "associated_company", nullable = true)
  private Company company;

}
