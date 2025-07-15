package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/offer-applications")
public class OfferApplicationController {

  @Autowired
  private OfferApplicationService offerApplicationService;

  /**
   * Endpoint to get all offer applications.
   *
   * @return ResponseEntity with a list of OfferApplicationDto
   */
  @Operation(
          summary = "Obtener todas las postulaciones a ofertas",
          description = "Retorna una lista de todas las postulaciones a ofertas disponibles dependiendo " +
                  " la compañía del usuario (en caso de tener compañía)."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Lista de postulaciones a ofertas obtenida exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"read", "write"}
  )
  @GetMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<Page<OfferApplicationDto>> getOfferApplications(
          Authentication auth,
          @ModelAttribute PaginationQueriesDto queries
          ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOffersApplications(auth, queries));
  }

  /**
   * Endpoint to get an offer application by its ID.
   *
   * @param id the ID of the offer application
   * @param auth the authentication object
   * @return ResponseEntity with the OfferApplicationDto
   */
  @Operation(
          summary = "Obtener una postulación a oferta por ID",
          description = "Retorna una postulación a oferta específica por su ID. " +
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
  @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"read", "write"}
  )
  @GetMapping(value = "/{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferApplicationDto> getOfferApplicationById(
          @PathVariable(value = "id") Long id,
          Authentication auth
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.getOfferApplicationsById(auth, id));
  }

  /**
   * Endpoint to create a new offer application.
   *
   * @param request the OfferApplicationRequest containing the details of the application
   * @return ResponseEntity with the created OfferApplicationDto
   */
  @Operation(
            summary = "Crear una nueva postulación a oferta",
            description = "Permite a un usuario crear una nueva postulación a una oferta. " +
                    "El usuario debe tener los permisos necesarios para realizar esta acción."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Postulación a oferta creada exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"read", "write"}
  )
  @PostMapping(value = "")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<OfferApplicationDto> createOfferApplication(
          Authentication auth,
          @Valid @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.createOfferApplication(auth, request));
  }

  /**
   * Endpoint to delete an offer application by its ID.
   *
   * @param id the ID of the offer application
   * @return ResponseEntity with the status of the deletion
   */
  @Operation(
          summary = "Actualiza una postulación a oferta por ID",
          description = "Permite a un usuario actualizar una postulación a oferta específica por su ID. "
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Postulación a oferta eliminada exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Postulación a oferta no encontrada",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"read", "write"}
  )
  @PutMapping(value = "{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<OfferApplicationDto> updateOfferApplication(
          Authentication auth,
          @PathVariable(value = "id") Long id,
          @RequestBody OfferApplicationRequest request
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.updateOfferApplication(auth, request, id));
  }

  @DeleteMapping(value = "{offerId}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<OfferApplicationDto> deleteOfferApplication(
          Authentication auth,
          @PathVariable(value = "offerId") Long offerId
  ) {

    return ResponseEntity.status(HttpStatus.OK).body(offerApplicationService.deleteRequest(auth, offerId));
  }
}
