package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/offer-applications")
public class OfferApplicationController {

  @Autowired
  private OfferApplicationService offerApplicationService;

  @GetMapping(value = "")
  @Secured("ROLE_" + UtemRoles.USER.name())
  public ResponseEntity<List<OfferApplicationDto>> getOfferApplications() {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOffers());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OfferApplicationDto> getOfferApplicationById(
          @PathVariable(value = "id") Long id
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOfferById(id));
  }

  @PostMapping(value = "")
  public ResponseEntity<OfferApplicationDto> createOfferApplication(
          @Valid @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.createRequest(request));
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<OfferApplicationDto> updateOfferApplication(
          @PathVariable(value = "id") Long id,
          @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.updateRequest(request, id));
  }
}
