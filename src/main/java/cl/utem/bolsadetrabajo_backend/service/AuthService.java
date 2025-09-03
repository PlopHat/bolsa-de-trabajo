package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthLoginRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRegisterRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
  public AuthResponse login(AuthLoginRequest request);
  public AuthResponse register(AuthRegisterRequest request);
}
