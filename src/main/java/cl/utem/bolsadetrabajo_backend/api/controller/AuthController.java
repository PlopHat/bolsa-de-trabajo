package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import cl.utem.bolsadetrabajo_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthResponse> login(
          @RequestBody final AuthRequest authRequest) {

    return ResponseEntity.status(HttpStatus.OK).body(authService.login(authRequest));
  }
}
