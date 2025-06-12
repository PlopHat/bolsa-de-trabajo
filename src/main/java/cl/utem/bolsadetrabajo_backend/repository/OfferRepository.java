package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.Company;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
  Offer getOfferById(Long id);

  Page<Offer> findAllByOfferAuthor_Company(Company offerAuthorCompany, Pageable pageable);
}