package cl.utem.bolsadetrabajo_backend.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class AuthResponse implements Serializable {

  @JsonProperty(value = "token")
  private String token;
}
