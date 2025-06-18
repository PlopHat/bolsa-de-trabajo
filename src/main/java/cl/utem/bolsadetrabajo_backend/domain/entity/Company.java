package cl.utem.bolsadetrabajo_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  @Column(name = "rut", nullable = false, unique = true, length = 8)
  private int rut;

  @Column(name = "digito_verificador", nullable = false, length = 1)
  private String DV;

  @Column(name = "fantasy_name", nullable = false)
  private String fantasyName;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false, updatable = false)
  private LocalDateTime updatedAt;

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


}
