package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.configuration.CustomUserDetailsObject;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
  public String extractUsername(String token);

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  public String generateToken(CustomUserDetailsObject userDetails);

  public String generateToken(Map<String, Object> extraClaims, CustomUserDetailsObject userDetails);

  public long getExpirationTime();

  public boolean isTokenValid(String token, UserDetails userDetails);
}
