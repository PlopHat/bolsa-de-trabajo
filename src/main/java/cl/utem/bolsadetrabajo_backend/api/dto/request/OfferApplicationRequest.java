package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OfferApplicationRequest {
  @JsonProperty(value = "offerId")
  @NotNull(message = "offerId must not be null")
  @Schema(
    description = "The ID of the offer to which the application is being made",
    example = "12345",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  private Long offerId;

  @JsonProperty(value = "offerApplicationStatus")
  @Schema(
    description = "The status of the offer application",
    example = "PENDING",
    requiredMode = Schema.RequiredMode.NOT_REQUIRED
  )
  private OfferApplicationStatus offerApplicationStatus;

}
