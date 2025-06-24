package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PaginationQueriesDto {
  @JsonProperty(value = "page")
  @Schema(
          description = "Página actual de la solicitud",
          example = "0",
          minimum = "0",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  @Min(0)
  private int page;

  @JsonProperty(value = "pageSize")
  @Schema(
          description = "Tamaño de las páginas (elementos por página)",
          example = "10",
          minimum = "1",
          maximum = "12",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  @Min(1)
  @Max(12)
  private int pageSize;

  @JsonProperty(value = "offset")
  @Schema(
          description = "Punto de inicio (offset)",
          example = "0",
          minimum = "0",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  @Min(0)
  private int offset;

  @JsonProperty(value = "sortBy")
  @Schema(
          description = "Ordernar por parámetro",
          example = "id",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String sortBy;

  @JsonProperty(value = "sortDirection")
  @Schema(
          description = "Dirección del orden",
          allowableValues = {
                  "asc",
                  "desc"
          },
          example = "asc",
          requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String sortDirection;

}
