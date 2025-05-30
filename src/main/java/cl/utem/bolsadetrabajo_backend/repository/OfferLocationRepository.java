package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.OfferLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferLocationRepository extends JpaRepository<OfferLocation, Long> {
}