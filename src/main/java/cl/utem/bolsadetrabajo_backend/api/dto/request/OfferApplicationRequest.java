package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferApplicationRequest {
  @JsonProperty(value = "offerId")
  @NotNull(message = "offerId must not be null")
  private Long offerId;

  @JsonProperty(value = "requestDate")
  @NotNull(message = "requestDate must not be null")
  private LocalDateTime requestDate;

  @JsonProperty(value = "offerApplicationStatus")
  @NotNull(message = "offerApplicationStatus must not be null")
  private OfferApplicationStatus offerApplicationStatus;

}
