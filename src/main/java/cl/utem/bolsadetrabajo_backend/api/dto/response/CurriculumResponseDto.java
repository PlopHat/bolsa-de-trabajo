package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.Curriculum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Curriculum}
 */
@Data
public class CurriculumResponseDto implements Serializable {

  @JsonProperty(value = "id")
  private Long id;

  @JsonProperty(value = "user")
  private UserResponse user;

  @JsonProperty(value = "createdAt")
  private LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  private LocalDateTime updatedAt;

  @JsonProperty(value = "firstName")
  private String firstName;

  @JsonProperty(value = "lastName")
  private String lastName;

  @JsonProperty(value = "email")
  private String email;

  @JsonProperty(value = "phoneNumber")
  private String phoneNumber;

  @JsonProperty(value = "address")
  private String address;

  @JsonProperty(value = "education")
  private String education;

  @JsonProperty(value = "experience")
  private String experience;

  @JsonProperty(value = "skills")
  private String skills;

  @JsonProperty(value = "certifications")
  private String certifications;

  @JsonProperty(value = "languages")
  private String languages;

  @JsonProperty(value = "referals")
  private String referals;

  public CurriculumResponseDto toDto(Curriculum cv) {
    this.id = cv.getId();
    this.createdAt = cv.getCreatedAt();
    this.updatedAt = cv.getUpdatedAt();
    this.firstName = cv.getFirstName();
    this.lastName = cv.getLastName();
    this.email = cv.getEmail();
    this.phoneNumber = cv.getPhoneNumber();
    this.address = cv.getAddress();
    this.education = cv.getEducation();
    this.experience = cv.getExperience();
    this.skills = cv.getSkills();
    this.certifications = cv.getCertifications();
    this.languages = cv.getLanguages();
    this.referals = cv.getReferals();
    this.user = new UserResponse().toDto(cv.getUser());

    return this;
  }

}