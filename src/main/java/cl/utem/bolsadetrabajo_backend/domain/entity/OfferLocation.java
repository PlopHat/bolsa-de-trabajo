package cl.utem.bolsadetrabajo_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offer_locations")
public class OfferLocation extends BaseEntity {
  @Column(name = "region", nullable = false)
  private String region;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "district", nullable = false)
  private String district;

  @Column(name = "address", nullable = true)
  private String address;

  @Column(name = "address_number", nullable = true)
  private String addressNumber;

}
