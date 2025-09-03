package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.OfferLocation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.OfferLocation}
 */
@Data
public class OfferLocationDto implements Serializable {
  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  @JsonProperty(value = "region")
  String region;

  @JsonProperty(value = "city")
  String city;

  @JsonProperty(value = "district")
  String district;

  @JsonProperty(value = "address")
  String address;

  @JsonProperty(value = "addressNumber")
  String addressNumber;

  public OfferLocationDto toDto(OfferLocation offerLocation) {
    this.id = offerLocation.getId();
    this.createdAt = offerLocation.getCreatedAt();
    this.updatedAt = offerLocation.getUpdatedAt();
    this.region = offerLocation.getRegion();
    this.city = offerLocation.getCity();
    this.district = offerLocation.getDistrict();
    this.address = offerLocation.getAddress();
    this.addressNumber = offerLocation.getAddressNumber();
    return this;
  }
}
