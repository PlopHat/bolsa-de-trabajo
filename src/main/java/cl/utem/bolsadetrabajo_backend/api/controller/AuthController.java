package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthLoginRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRegisterRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import cl.utem.bolsadetrabajo_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthResponse> login(
          @Valid @RequestBody final AuthLoginRequest request) {

    return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<AuthResponse> register(
          @Valid @RequestBody final AuthRegisterRequest request) {

    return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
  }
}
