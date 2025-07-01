package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.UserRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import cl.utem.bolsadetrabajo_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

  @Autowired
  private UserService userService;

  /**
   * Endpoint to get all users.
   *
   * @return ResponseEntity with a list of UserResponse
   */
  @Operation(
          summary = "Obtener todos los usuarios",
          description = "Retorna una lista de todos los usuarios registrados en el sistema."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Lista de usuarios obtenida exitosamente"
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
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())")
  public ResponseEntity<List<UserResponse>> getAllUsers() {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  /**
   * Endpoint to get a user by its ID.
   *
   * @param id the ID of the user
   * @return ResponseEntity with UserResponse
   */
  @Operation(
          summary = "Obtener usuario por ID",
          description = "Retorna un usuario específico por su ID."
  )

  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Usuario obtenido exitosamente"
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Usuario no encontrado",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @SecurityRequirement(
          name = "bearerAuth",
          scopes = {"read", "write"}
  )
  @GetMapping(value = "{id}")
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_USER.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name()) or " +
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_COMPANY.name())")
  public ResponseEntity<UserResponse> getUserById(
          @PathVariable Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
  }

  /**
   * Endpoint to create a new user.
   *
   * @param auth the authentication object
   * @param userRequest the request body containing user details
   * @return ResponseEntity with UserResponse
   */
  @Operation(
          summary = "Crear un nuevo usuario",
          description = "Crea un nuevo usuario en el sistema."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "201",
                  description = "Usuario creado exitosamente"
          ),
          @ApiResponse(
                  responseCode = "400",
                  description = "Solicitud incorrecta, datos del usuario no válidos",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          ),
          @ApiResponse(
                  responseCode = "403",
                  description = "Acceso denegado, el usuario no tiene los permisos necesarios",
                  content = @Content(schema = @Schema(implementation = ProblemDetail.class))
          )
  })
  @PreAuthorize(value =
          "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())"
  )
  @PostMapping(value = "")
  public ResponseEntity<UserResponse> createUser(
          Authentication auth,
          @RequestBody UserRequest userRequest
  ) {

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(auth, userRequest));
  }

}
