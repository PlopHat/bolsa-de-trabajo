package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.Curriculum;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
  Optional<Curriculum> findCurriculumByUser_Rut(String userRut);

  Optional<Curriculum> findCurriculumByUser(UtemUser user);
}