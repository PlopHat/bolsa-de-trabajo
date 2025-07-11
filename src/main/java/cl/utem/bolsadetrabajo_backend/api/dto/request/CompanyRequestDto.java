package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Company}
 */
@Data
public class CompanyRequestDto {

  @JsonProperty(value = "rut")
  @NotBlank
  @Schema(
          description = "RUT de la empresa con o sin puntos (formato: 12345678-9 o 12.345.678-9)",
          example = "12.345.678-9",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String rut;

  @JsonProperty(value = "companyName")
  @NotBlank
  @Schema(
          description = "Nombre de fantasía de la empresa",
          example = "Empresa S.A.",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String fantasyName;
}
