package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
  public AuthResponse login(AuthRequest authRequest);
  public AuthResponse register(AuthRequest authRequest);
}
