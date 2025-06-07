package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.ExperienceRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.ExperienceDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Experience;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.repository.ExperienceRepository;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienceServiceApiImpl implements ExperienceService {

  @Autowired
  private ExperienceRepository experienceRepository;

  @Autowired
  private UtemUserRepository utemUserRepository;

  @Override
  public List<ExperienceDto> findAll() {
    List<Experience> experiences = experienceRepository.findAll();

    List<ExperienceDto> experienceDtos = new ArrayList<ExperienceDto>();
    for (Experience experience : experiences) {
      experienceDtos.add(new ExperienceDto().toDto(experience));
    }

    return experienceDtos;
  }

  @Override
  public ExperienceDto findById(Long id) {
    Experience experience = experienceRepository.findById(id).orElse(null);
    if (experience != null) {
      return new ExperienceDto().toDto(experience);
    }
    return null; // or throw an exception if preferred
  }

  @Override
  public ExperienceDto createExperience(ExperienceRequest experienceRequest) {

    UtemUser user = utemUserRepository.findById(experienceRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found with id: " + experienceRequest.getUserId()));

    Experience experience = new Experience();
      experience.setCompanyName(experienceRequest.getCompanyName());
      experience.setTitle(experienceRequest.getTitle());
      experience.setDescription(experienceRequest.getDescription());
      experience.setStartDate(experienceRequest.getStartDate());
      experience.setEndDate(experienceRequest.getEndDate());

    Experience savedExperience = experienceRepository.save(experience);
    return new ExperienceDto().toDto(savedExperience);
  }
}
