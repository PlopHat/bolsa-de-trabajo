package cl.utem.bolsadetrabajo_backend.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExperienceRequest {

  // USER ID, temporal, will be replaced with jwtToken later  //
  @JsonProperty(value = "userId")
  @NotNull(message = "userId must not be null")
  private Long userId;
  //                                                          //


  @JsonProperty(value = "companyName")
  @NotNull(message = "companyName must not be null")
  private String companyName;

  @JsonProperty(value = "title")
  @NotNull(message = "title must not be null")
  private String title;

  @JsonProperty(value = "description")
  @NotNull(message = "description must not be null")
  private String description;

  @JsonProperty(value = "startDate")
  @NotNull(message = "startDate must not be null")
  private LocalDateTime startDate;

  @JsonProperty(value = "endDate")
  private LocalDateTime endDate;

}
