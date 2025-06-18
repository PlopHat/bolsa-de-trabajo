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
public class CompanyDto implements Serializable {
  @JsonProperty(value = "rut")
  int rut;

  @JsonProperty(value = "DV")
  String DV;

  @JsonProperty(value = "fantasyName")
  String fantasyName;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  public CompanyDto toDto(Company company) {
    this.rut = company.getRut();
    this.DV = company.getDV();
    this.fantasyName = company.getFantasyName();
    this.createdAt = company.getCreatedAt();
    this.updatedAt = company.getUpdatedAt();
    return this;
  }
}