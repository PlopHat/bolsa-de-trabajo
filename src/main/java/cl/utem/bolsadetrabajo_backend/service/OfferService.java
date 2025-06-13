package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface OfferService {
  public Page<OfferResponse> getOffers(Authentication auth, PaginationQueriesDto queries);
  public OfferResponse getOfferById(Authentication auth, Long id);
  // public OfferResponse createOffer();
  // public OfferResponse updateOfferById(Long id);
  // public OfferResponse deleteOfferById(Long id);

}
