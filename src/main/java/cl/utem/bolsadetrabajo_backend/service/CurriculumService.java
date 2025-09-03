package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CurriculumRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CurriculumResponseDto;
import org.springframework.security.core.Authentication;

public interface CurriculumService {

  public CurriculumResponseDto createCurriculum(Authentication auth, CurriculumRequestDto request);

  CurriculumResponseDto editCurriculum(Authentication auth, CurriculumRequestDto request);

  public CurriculumResponseDto getCurriculumByUserRut(Authentication auth, String rut);
}
