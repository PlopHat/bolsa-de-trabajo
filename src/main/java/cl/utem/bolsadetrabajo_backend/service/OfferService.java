package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface OfferService {
  public Page<OfferResponse> getOffers(Authentication auth, PaginationQueriesDto queries);
  public OfferResponse getOfferById(Authentication auth, Long id);
  public OfferResponse updateOffer(Authentication auth, Long id, OfferRequestDto req);
  public OfferResponse createOffer(Authentication auth, OfferRequestDto req);
  public OfferResponse deleteOffer(Authentication auth, Long id);

}
