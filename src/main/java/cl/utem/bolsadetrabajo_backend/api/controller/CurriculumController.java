package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CurriculumRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CurriculumResponseDto;
import cl.utem.bolsadetrabajo_backend.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/curriculums")
public class CurriculumController {

  @Autowired
  private CurriculumService curriculumService;

  @GetMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<CurriculumResponseDto> getCurriculumByUserId(Authentication auth, Long userId) {

    return ResponseEntity.status(HttpStatus.OK).body(curriculumService.getCurriculumByUserId(auth, userId));
  }

  @PostMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<CurriculumResponseDto> createCurriculum(Authentication auth, CurriculumRequestDto request) {

    return ResponseEntity.status(HttpStatus.CREATED).body(curriculumService.createCurriculum(auth, request));
  }

}
