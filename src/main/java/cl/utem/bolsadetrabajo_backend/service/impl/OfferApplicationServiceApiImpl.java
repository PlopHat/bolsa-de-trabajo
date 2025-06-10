package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.repository.OfferApplicationRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferApplicationServiceApiImpl implements OfferApplicationService {

  @Autowired
  private OfferApplicationRepository offerApplicationRepository;

  public List<OfferApplicationDto> getOffers() {
    List<OfferApplication> offerApplications = offerApplicationRepository.findAll();

    List<OfferApplicationDto> responses = new ArrayList<>();
    for (OfferApplication offerApplication : offerApplications) {
      responses.add(new OfferApplicationDto().toDto(offerApplication));
    }

    return responses;
  }

  public OfferApplicationDto getOfferById(Long id) {
    OfferApplication offerApplication = offerApplicationRepository.findById(id).orElse(null);

    if (offerApplication == null) {
      return null;
    }

    return new OfferApplicationDto().toDto(offerApplication);
  }

  public OfferApplicationDto createOffer(OfferApplicationRequest request) {
    // Validations

    Optional<OfferApplication> offer = offerApplicationRepository.findById(request.getOfferId());
    if (offer.isEmpty()) {
      // manejar null
      return null;
    }


    OfferApplication offerApplication = new OfferApplication();
    // Get user from context when context is available
    // offerApplication.setUser();

    offerApplication.setOfferApplicationRequest();
    return null;
  }

}
