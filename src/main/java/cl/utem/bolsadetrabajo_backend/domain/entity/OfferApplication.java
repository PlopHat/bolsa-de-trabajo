package cl.utem.bolsadetrabajo_backend.domain.entity;

import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "offer_applications")
public class OfferApplication extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "appliant_user")
  @NotNull(message = "user must exist on an offer application")
  private UtemUser User;

  @ManyToOne
  @JoinColumn(name = "offer")
  @NotNull(message = "offer application must have an offer")
  private Offer offer;

  @Column(name = "application_request_date")
  @NotNull(message = "offer application must have an request date")
  private LocalDateTime RequestDate;

  @Column(name = "application_status")
  @JdbcType(PostgreSQLEnumJdbcType.class)
  @NotNull(message = "offer application must have an application status")
  private OfferApplicationStatus offerApplicationStatus;
}