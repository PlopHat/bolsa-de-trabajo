package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Company}
 */
@Data
public class CompanyRequestDto {
  @JsonProperty(value = "rut")
  @NotNull
  @Schema(
      description = "RUT de la empresa",
      example = "12345678-9",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  int rut;

  @JsonProperty(value = "companyName")
  @NotNull
  @Schema(
      description = "Nombre de la empresa",
      example = "Empresa S.A.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  String fantasyName;
}