package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtemUserRepository extends JpaRepository<UtemUser, Long> {
  UtemUser getUserById(Long id);

  Optional<UtemUser> getUserByEmail(String email);

  Optional<UtemUser> findByEmail(String email);
}