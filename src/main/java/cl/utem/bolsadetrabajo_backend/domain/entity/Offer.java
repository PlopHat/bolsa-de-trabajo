package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkMode;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bdt_offers")
public class Offer extends BaseEntity {
  @Column(name = "offer_name", nullable = false)
  private String offerName;

  @Column(name = "offer_description", nullable = false)
  private String offerDescription;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "salary", nullable = false)
  private Double salary;

  @Column(name = "work_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private WorkType workType;

  @Column(name = "work_mode", nullable = false)
  @Enumerated(EnumType.STRING)
  private WorkMode workMode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "offerLocation", nullable = false)
  private OfferLocation offerLocation;

}