package cl.utem.bolsadetrabajo_backend.api.dto.request;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkMode;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferRequestDto {
  @JsonProperty(value = "offerName")
  @NotEmpty(message = "Offer name cannot be empty")
  @NotNull(message = "Offer name cannot be null")
  String offerName;

  @JsonProperty(value = "offerDescription")
  @NotEmpty
  @NotNull
  String offerDescription;

  @JsonProperty(value = "startDate")
  @NotEmpty
  @NotNull
  LocalDateTime startDate;

  @JsonProperty(value = "endDate")
  @NotEmpty
  @NotNull
  LocalDateTime endDate;

  @JsonProperty(value = "salary")
  @NotEmpty
  @NotNull
  Double salary;

  @JsonProperty(value = "workType")
  @NotEmpty
  @NotNull
  WorkType workType;

  @JsonProperty(value = "workMode")
  @NotEmpty
  @NotNull
  WorkMode workMode;

  @JsonProperty(value = "offerLocationId")
  @NotEmpty
  @NotNull
  Long offerLocationId;

  @JsonProperty(value = "offerAuthorId")
  @NotEmpty
  @NotNull
  Long offerAuthorId;

}
