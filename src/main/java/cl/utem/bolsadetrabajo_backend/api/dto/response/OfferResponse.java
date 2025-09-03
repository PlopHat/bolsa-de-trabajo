package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkMode;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Offer}
 */
@Data
public class OfferResponse implements Serializable {
  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  @JsonProperty(value = "offerName")
  String offerName;

  @JsonProperty(value = "offerDescription")
  String offerDescription;

  @JsonProperty(value = "startDate")
  LocalDateTime startDate;

  @JsonProperty(value = "endDate")
  LocalDateTime endDate;

  @JsonProperty(value = "salary")
  Double salary;

  @JsonProperty(value = "workType")
  WorkType workType;

  @JsonProperty(value = "workMode")
  WorkMode workMode;

  @JsonProperty(value = "offerAuthor")
  UserResponse offerAuthor;

  public OfferResponse toDto(Offer offer) {
    this.id = offer.getId();
    this.createdAt = offer.getCreatedAt();
    this.updatedAt = offer.getUpdatedAt();
    this.offerName = offer.getOfferName();
    this.offerDescription = offer.getOfferDescription();
    this.startDate = offer.getStartDate();
    this.endDate = offer.getEndDate();
    this.salary = offer.getSalary();
    this.workType = offer.getWorkType();
    this.workMode = offer.getWorkMode();
    this.offerAuthor = new UserResponse().toDto(offer.getOfferAuthor());
    return this;
  }
}
