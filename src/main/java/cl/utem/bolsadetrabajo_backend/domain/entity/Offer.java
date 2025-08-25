package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkMode;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.WorkType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;
import java.util.List;

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
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private WorkType workType;

  @Column(name = "work_mode", nullable = false)
  @Enumerated(EnumType.STRING)
  @JdbcType(PostgreSQLEnumJdbcType.class)
  private WorkMode workMode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "offer_location", nullable = true)
  private OfferLocation offerLocation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "offer_author")
  private UtemUser offerAuthor;

  @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OfferApplication> offerApplications;

}
