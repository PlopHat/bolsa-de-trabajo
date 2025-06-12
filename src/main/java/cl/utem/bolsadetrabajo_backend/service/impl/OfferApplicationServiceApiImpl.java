package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import cl.utem.bolsadetrabajo_backend.repository.OfferApplicationRepository;
import cl.utem.bolsadetrabajo_backend.repository.OfferRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferApplicationServiceApiImpl implements OfferApplicationService {

  @Autowired
  private OfferApplicationRepository offerApplicationRepository;
  @Autowired
  private OfferRepository offerRepository;

  @Override
  public List<OfferApplicationDto> getOffers() {
    List<OfferApplication> offerApplications = offerApplicationRepository.findAll();

    List<OfferApplicationDto> responses = new ArrayList<>();
    for (OfferApplication offerApplication : offerApplications) {
      responses.add(new OfferApplicationDto().toDto(offerApplication));
    }

    return responses;
  }

  @Override
  public OfferApplicationDto getOfferById(Long id) {
    OfferApplication offerApplication = offerApplicationRepository.findById(id).orElse(null);

    if (offerApplication == null) {
      return null;
    }

    return new OfferApplicationDto().toDto(offerApplication);
  }

  @Override
  public OfferApplicationDto createRequest(OfferApplicationRequest request) {
    // Validations


    // Handle null using exception
    Offer offer = offerRepository.findById(request.getOfferId()).orElse(null);

    OfferApplication offerApplication = new OfferApplication();
    // Get user from context when context is available
    //  offerApplication.setUser();

      offerApplication.setOffer(offer);
      offerApplication.setRequestDate(LocalDateTime.now());
      offerApplication.setOfferApplicationStatus(OfferApplicationStatus.UNSEEN);

    return new OfferApplicationDto().toDto(offerApplicationRepository.save(offerApplication));
  }

  @Override
  public OfferApplicationDto updateRequest(OfferApplicationRequest request, Long id) {

    // Validations

    Offer offer = offerRepository.findById(id).orElse(null);
    OfferApplication offerApplication = new OfferApplication();
    offerApplication.setOfferApplicationStatus(request.getOfferApplicationStatus());

    return new  OfferApplicationDto().toDto(offerApplicationRepository.save(offerApplication));
  }

  @Override
  public Type deleteRequest(Long id) throws Exception {

    // Validations

    OfferApplication offerApplication = offerApplicationRepository.findById(id).orElse(null);

    if (offerApplication != null) {
      offerApplicationRepository.delete(offerApplication);
    } else {

      // handle null 'NotFoundException'
      throw new Exception();
    }

    return null;
  }

}
