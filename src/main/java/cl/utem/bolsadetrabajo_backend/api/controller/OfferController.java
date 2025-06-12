package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/offers")
public class OfferController {
  @Autowired
  private OfferService offerService;

  @GetMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())") // Definir alcances de los roles
  public ResponseEntity<List<OfferResponse>> getAllOffers() {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOffers());
  }

  @GetMapping(value = "{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferResponse> getOfferById(
          @PathVariable("id") Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOfferById(id));
  }
}
