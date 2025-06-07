package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.ExperienceRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.ExperienceDto;

import java.util.List;

public interface ExperienceService {
  public List<ExperienceDto> findAll();
  public ExperienceDto findById(Long id);
  public ExperienceDto createExperience(ExperienceRequest experienceRequest);
}
