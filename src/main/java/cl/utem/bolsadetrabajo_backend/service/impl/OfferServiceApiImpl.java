package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.repository.OfferRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceApiImpl implements OfferService {

  @Autowired
  private OfferRepository offerRepository;

  @Override
  public List<OfferResponse> getOffers() {
    List<Offer> offers = offerRepository.findAll();

    List<OfferResponse> offerResponses = new ArrayList<>();
    for (Offer offer : offers) {
      offerResponses.add(new OfferResponse().toDto(offer));
    }

    return offerResponses;
  }

}
