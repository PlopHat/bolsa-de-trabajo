package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CurriculumRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CurriculumResponseDto;
import org.springframework.security.core.Authentication;

public interface CurriculumService {

  public CurriculumResponseDto createCurriculum(Authentication auth, CurriculumRequestDto request);
  public CurriculumResponseDto getCurriculumByUserId(Authentication auth, Long userId);
}
