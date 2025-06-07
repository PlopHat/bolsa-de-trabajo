package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication}
 */
@Data
public class OfferApplicationDto implements Serializable {
  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  @JsonProperty(value = "user")
  @NotNull(message = "user must exist on an offer application")
  UserResponse User;

  @JsonProperty(value = "offerApplicationRequest")
  @NotNull(message = "offer application must have an offer")
  OfferResponse offerApplicationRequest;

  @JsonProperty(value = "requestDate")
  @NotNull(message = "offer application must have an request date")
  LocalDateTime RequestDate;

  @JsonProperty(value = "offerApplicationStatus")
  @NotNull(message = "offer application must have an application status")
  OfferApplicationStatus offerApplicationStatus;

  OfferApplicationDto toDto(OfferApplication offerApplication) {
    this.id = offerApplication.getId();
    this.createdAt = offerApplication.getCreatedAt();
    this.updatedAt = offerApplication.getUpdatedAt();
    this.User = new UserResponse().toDto(offerApplication.getUser());
    this.offerApplicationRequest = new OfferResponse().toDto(offerApplication.getOfferApplicationRequest());
    this.RequestDate = offerApplication.getRequestDate();
    this.offerApplicationStatus = offerApplication.getOfferApplicationStatus();
    return this;
  }
}