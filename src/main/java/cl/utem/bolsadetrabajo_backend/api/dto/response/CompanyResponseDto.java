package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Company}
 */
@Data
public class CompanyResponseDto implements Serializable {

  @JsonProperty(value = "rut")
  private String rut; // Formato: 12.345.678-9

  @JsonProperty(value = "fantasyName")
  private String fantasyName;

  @JsonProperty(value = "createdAt")
  private LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  private LocalDateTime updatedAt;

  public CompanyResponseDto toDto(Company company) {
    this.rut = company.getRut();
    this.fantasyName = company.getFantasyName();
    this.createdAt = company.getCreatedAt();
    this.updatedAt = company.getUpdatedAt();
    return this;
  }
}
