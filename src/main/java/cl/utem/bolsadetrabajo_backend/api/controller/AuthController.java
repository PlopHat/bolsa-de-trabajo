package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthLoginRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRegisterRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import cl.utem.bolsadetrabajo_backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Operaciones de autenticación")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Operation(
          summary = "Iniciar sesión",
          description = "Autentica a un usuario y retorna un token"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Autenticación exitosa"
          ),
          @ApiResponse(
                  responseCode = "401",
                  description = "Credenciales inválidas"
          )
  })
  @PostMapping(value = "/login")
  public ResponseEntity<AuthResponse> login(
          @Valid @RequestBody final AuthLoginRequest request) {

    return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));
  }

  @Operation(
          summary = "Registrar usuario",
          description = "Registra un nuevo usuario y retorna un token"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Autenticación exitosa"
          ),
          @ApiResponse(
                  responseCode = "401",
                  description = "Credenciales inválidas"
          )
  })
  @PostMapping(value = "/register")
  public ResponseEntity<AuthResponse> register(
          @Valid @RequestBody final AuthRegisterRequest request) {

    return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
  }
}
