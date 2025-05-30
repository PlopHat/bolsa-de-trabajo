package cl.utem.bolsadetrabajo_backend.api.dto.response;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class AuthResponse implements Serializable {
  private String token;
}
