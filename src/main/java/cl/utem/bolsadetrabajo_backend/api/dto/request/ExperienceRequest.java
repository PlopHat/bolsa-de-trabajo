package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExperienceRequest {

  // USER ID, temporal, will be replaced with jwtToken later  //
  @JsonProperty(value = "userId")
  @NotNull(message = "userId must not be null")
  private Long userId;
  //                                                          //

  @JsonProperty(value = "companyName")
  @NotNull(message = "companyName must not be null")
  @Schema(
      description = "Nombre de la empresa donde se adquirió la experiencia",
      example = "Empresa S.A.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String companyName;

  @JsonProperty(value = "title")
  @NotNull(message = "title must not be null")
  @Schema(
      description = "Título del puesto o cargo desempeñado",
      example = "Desarrollador de Software",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String title;

  @JsonProperty(value = "description")
  @NotNull(message = "description must not be null")
  @Schema(
      description = "Descripción de las responsabilidades y logros en el puesto",
      example = "Desarrollo de aplicaciones web utilizando Java y Spring Boot.",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  private String description;

  @JsonProperty(value = "startDate")
  @NotNull(message = "startDate must not be null")
  @Schema(
      description = "Fecha de inicio de la experiencia laboral",
      example = "2022-01-01T00:00:00",
      requiredMode = Schema.RequiredMode.REQUIRED
  )
  private LocalDateTime startDate;

  @JsonProperty(value = "endDate")
  @Schema(
      description = "Fecha de finalización de la experiencia laboral. Si aún está activo, puede ser nulo.",
      example = "2023-01-01T00:00:00",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private LocalDateTime endDate;

}
