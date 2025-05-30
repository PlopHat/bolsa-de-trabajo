package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import cl.utem.bolsadetrabajo_backend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceApiImpl implements AuthService {

  @Override
  public AuthResponse login(AuthRequest authRequest) {

    return null;
  }

  @Override
  public AuthResponse register(AuthRequest authRequest) {

    return null;
  }
}