package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
