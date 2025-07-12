package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CurriculumRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CurriculumResponseDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Curriculum;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.repository.CurriculumRepository;
import cl.utem.bolsadetrabajo_backend.service.CurriculumService;
import cl.utem.bolsadetrabajo_backend.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CurriculumServiceApiImpl implements CurriculumService {

  @Autowired
  private CurriculumRepository curriculumRepository;

  @Autowired
  private ContextUtils contextUtils;

  @Override
  public CurriculumResponseDto createCurriculum(Authentication auth, CurriculumRequestDto request) {

    UtemUser user = contextUtils.getUserFromContext(auth);

    Curriculum curriculum = new Curriculum();
      curriculum.setUser(user);
      curriculum.setSkills(request.getSkills());
      curriculum.setFirstName(request.getFirstName());
      curriculum.setLastName(request.getLastName());
      curriculum.setEmail(request.getEmail());
      curriculum.setPhoneNumber(request.getPhoneNumber());
      curriculum.setAddress(request.getAddress());
      curriculum.setEducation(request.getEducation());
      curriculum.setExperience(request.getExperience());
      curriculum.setCertifications(request.getCertifications());
      curriculum.setLanguages(request.getLanguages());
      curriculum.setReferences(request.getReferences());

    return new CurriculumResponseDto().toDto(curriculumRepository.save(curriculum));
  }

  @Override
  public CurriculumResponseDto getCurriculumByUserId(Authentication auth, Long userId) {
    UtemUser user = contextUtils.getUserFromContext(auth);

    if (!user.getId().equals(userId) || user.getRole() != UtemRoles.ROLE_ADMINISTRATOR) {
      throw new IllegalArgumentException("You can only access your own curriculum.");
    }

    Curriculum curriculum = curriculumRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("Curriculum not found for user ID: " + userId));

    return new CurriculumResponseDto().toDto(curriculum);
  }

}
