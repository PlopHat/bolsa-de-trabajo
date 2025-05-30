package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {
  public List<OfferResponse> getOffers();
  public OfferResponse getOfferById(Long id);
  // public OfferResponse createOffer();
  // public OfferResponse updateOfferById(Long id);
  // public OfferResponse deleteOfferById(Long id);

}
