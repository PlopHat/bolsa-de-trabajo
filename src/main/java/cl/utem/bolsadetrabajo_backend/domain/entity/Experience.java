package cl.utem.bolsadetrabajo_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "experience")
public class Experience extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "app_user", nullable = false)
  private UtemUser user;

  @Column(name = "company_name", nullable = false)
  private String companyName;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = true)
  private LocalDateTime endDate;
}