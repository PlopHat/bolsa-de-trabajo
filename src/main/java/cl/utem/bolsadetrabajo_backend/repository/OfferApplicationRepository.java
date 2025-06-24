package cl.utem.bolsadetrabajo_backend.repository;

import cl.utem.bolsadetrabajo_backend.domain.entity.Company;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferApplicationRepository extends JpaRepository<OfferApplication, Long> {
  Page<OfferApplication> findAllByUser(UtemUser user, Pageable pageable);
  Page<OfferApplication> findAllByOffer_OfferAuthor_Company(Company offerOfferAuthorCompany, Pageable pageable);
}