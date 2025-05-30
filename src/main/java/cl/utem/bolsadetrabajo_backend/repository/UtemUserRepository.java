package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtemUserRepository extends JpaRepository<UtemUser, Long> {
  UtemUser getUserById(Long id);
}