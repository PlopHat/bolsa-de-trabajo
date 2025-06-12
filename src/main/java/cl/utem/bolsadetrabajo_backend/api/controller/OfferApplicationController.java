package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/offer-applications")
public class OfferApplicationController {

  @Autowired
  private OfferApplicationService offerApplicationService;

  @GetMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).COMPANY.name())")
  public ResponseEntity<List<OfferApplicationDto>> getOfferApplications() {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOffers());
  }


  @GetMapping(value = "/{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferApplicationDto> getOfferApplicationById(
          @PathVariable(value = "id") Long id,
          Authentication auth
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOfferById(id));
  }

  @PostMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<OfferApplicationDto> createOfferApplication(
          @Valid @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.createRequest(request));
  }

  @PutMapping(value = "{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<OfferApplicationDto> updateOfferApplication(
          @PathVariable(value = "id") Long id,
          @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.updateRequest(request, id));
  }
}
