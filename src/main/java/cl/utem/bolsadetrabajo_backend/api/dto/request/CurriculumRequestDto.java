package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link cl.utem.bolsadetrabajo_backend.domain.entity.Curriculum}
 */
@Data
public class CurriculumRequestDto implements Serializable {

  @JsonProperty(value = "id")
  Long id;

  @JsonProperty(value = "firstName")
  String firstName;

  @JsonProperty(value = "lastName")
  String lastName;

  @JsonProperty(value = "email")
  String email;

  @JsonProperty(value = "phoneNumber")
  String phoneNumber;

  @JsonProperty(value = "address")
  String address;

  @JsonProperty(value = "education")
  String education;

  @JsonProperty(value = "experience")
  String experience;

  @JsonProperty(value = "skills")
  String skills;

  @JsonProperty(value = "certifications")
  String certifications;

  @JsonProperty(value = "languages")
  String languages;

  @JsonProperty(value = "references")
  String references;

}