package cl.utem.bolsadetrabajo_backend.utils;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ContextUtils {
  private final UtemUserRepository userRepository;

  public ContextUtils(UtemUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UtemUser getUserFromContext(Authentication auth) {
    String email = auth.getName();
    return userRepository.findByEmail(email).orElseThrow(CustomEntityNotFoundException::new);
  }
}
