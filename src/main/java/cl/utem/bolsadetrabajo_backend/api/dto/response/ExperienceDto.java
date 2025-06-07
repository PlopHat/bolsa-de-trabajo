package cl.utem.bolsadetrabajo_backend.api.dto.response;

import cl.utem.bolsadetrabajo_backend.domain.entity.Experience;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Experience}
 */
@Data
public class ExperienceDto implements Serializable {
  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "createdAt")
  LocalDateTime createdAt;

  @JsonProperty(value = "updatedAt")
  LocalDateTime updatedAt;

  @JsonProperty(value = "user")
  UserResponse user;

  @JsonProperty(value = "companyName")
  String companyName;

  @JsonProperty(value = "title")
  String title;

  @JsonProperty(value = "description")
  String description;

  @JsonProperty(value = "startDate")
  LocalDateTime startDate;

  @JsonProperty(value = "endDate")
  LocalDateTime endDate;

  public ExperienceDto toDto(Experience experience) {
    this.id = experience.getId();
    this.createdAt = experience.getCreatedAt();
    this.updatedAt = experience.getUpdatedAt();
    this.user = new UserResponse().toDto(experience.getUser());
    this.companyName = experience.getCompanyName();
    this.title = experience.getTitle();
    this.description = experience.getDescription();
    this.startDate = experience.getStartDate();
    this.endDate = experience.getEndDate();
    return this;
  }
}