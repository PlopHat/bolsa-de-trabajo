package cl.utem.bolsadetrabajo_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "curriculums")
public class Curriculum extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private UtemUser user;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = true)
  private String lastName;

  @Column(name = "email", nullable = true)
  private String email;

  @Column(name = "phone_number", nullable = true)
  private String phoneNumber;

  @Column(name = "address", nullable = true)
  private String address;

  @Column(name = "education", nullable = true)
  private String education;

  @Column(name = "experience", nullable = true)
  private String experience;

  @Column(name = "skills", nullable = true)
  private String skills;

  @Column(name = "certifications", nullable = true)
  private String certifications;

  @Column(name = "languages", nullable = true)
  private String languages;

  @Column(name = "references", nullable = true)
  private String references;

}
