package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/offers")
public class OfferController {
  @Autowired
  private OfferService offerService;

  /**
   * Endpoint to get all offers.
   *
   * @return ResponseEntity with a list of OfferResponse
   */
  @Operation(
          summary = "Obtener todas las ofertas",
          description = "Retorna una lista de todas las ofertas disponibles."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Lista de ofertas obtenida exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @GetMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())") // Definir alcances de los roles
  public ResponseEntity<Page<OfferResponse>> getAllOffers(
          @ModelAttribute PaginationQueriesDto queryParams,
          Authentication auth
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOffers(auth, queryParams));
  }

  /**
   * Endpoint to get an offer by its ID.
   *
   * @param id the ID of the offer
   * @return ResponseEntity with OfferResponse
   */
  @Operation(
          summary = "Obtener oferta por ID",
          description = "Retorna una oferta específica por su ID."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Oferta obtenida exitosamente"
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Oferta no encontrada",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @GetMapping(value = "{id}")
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferResponse> getOfferById(
          Authentication auth,
          @PathVariable("id") Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOfferById(auth ,id));
  }

  @GetMapping(value = "{offerId}/applications")
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<Page<OfferApplicationDto>> getOfferApplications(
          Authentication auth,
          @PathVariable("offerId") Long offerId,
          @ModelAttribute PaginationQueriesDto queryParams
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOfferApplications(auth, offerId, queryParams));
  }

  @Operation(
          summary = "Obtener una postulación a oferta por ID",
          description = "Retorna una postulación a oferta específica por su ID y usuario. " +
                  "El usuario debe tener los permisos necesarios para acceder a esta información."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Postulación a oferta obtenida exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios" +
                          "o no es de una compañía asociada a la oferta",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Postulación a oferta no encontrada",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @GetMapping(value = "{offerId}/applications/{userId}")
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())"
  )
  public ResponseEntity<OfferApplicationDto> getOfferApplicationById(
          Authentication auth,
          @PathVariable("offerId") Long offerId,
          @PathVariable("userId") Long userId
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.getOfferApplicationById(auth, offerId, userId));
  }

  @Operation(
          summary = "Crear una oferta laboral",
          description = "Crea una oferta laboral dado el schema provisto"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Oferta creada satisfactoriamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "No autorizado",
                  content =  @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @PostMapping(value = "")
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferResponse> createOffer(
          Authentication auth,
          @RequestBody OfferRequestDto request
  ) {

    return ResponseEntity.status(HttpStatus.CREATED).body(offerService.createOffer(auth, request));
  }

  @Operation(
          summary = "Actualiza una oferta laboral",
          description = "Actualiza una oferta laboral dado el schema provisto"
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Oferta creada satisfactoriamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "No autorizado",
                  content =  @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @PutMapping(value = "{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())"
  )
  public  ResponseEntity<OfferResponse> updateOffer(
          Authentication auth,
          @PathVariable(value = "id") Long id,
          @RequestBody OfferRequestDto request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.updateOffer(auth, id, request));
  }

  @DeleteMapping(value = "{id}")
  @PreAuthorize
  (value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferResponse> deleteOffer(
          Authentication auth,
          @PathVariable(value = "id") Long id
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerService.deleteOffer(auth, id));
  }
}
