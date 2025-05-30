package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication}
 */
@Value
public class OfferApplicationDto implements Serializable {
  Long id;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  @NotNull(message = "user must exist on an offer application")
  UserResponse User;
  @NotNull(message = "offer application must have an offer")
  OfferResponse offerApplicationRequest;
  @NotNull(message = "offer application must have an request date")
  LocalDateTime RequestDate;
  @NotNull(message = "offer application must have an application status")
  OfferApplicationStatus offerApplicationStatus;
}