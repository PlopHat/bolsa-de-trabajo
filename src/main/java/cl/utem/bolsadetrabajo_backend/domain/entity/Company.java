package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company {

  @Id
  @Column(name = "rut", nullable = false, unique = true, length = 12)
  private String rut;  // Siempre se guarda en formato: 12.345.678-9

  @Column(name = "fantasy_name", nullable = false)
  private String fantasyName;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

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
}
