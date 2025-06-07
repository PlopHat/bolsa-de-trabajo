package cl.utem.bolsadetrabajo_backend.api.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExperienceRequest {
  private String companyName;
  private String title;
  private String description;
  private LocalDateTime startDate;
  private LocalDateTime endDate;

}
