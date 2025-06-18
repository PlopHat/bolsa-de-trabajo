package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.OfferApplicationStatus;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import cl.utem.bolsadetrabajo_backend.repository.OfferApplicationRepository;
import cl.utem.bolsadetrabajo_backend.repository.OfferRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferApplicationService;
import cl.utem.bolsadetrabajo_backend.utils.ContextUtils;
import cl.utem.bolsadetrabajo_backend.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Service
public class OfferApplicationServiceApiImpl implements OfferApplicationService {

  @Autowired
  private OfferApplicationRepository offerApplicationRepository;
  @Autowired
  private OfferRepository offerRepository;
  @Autowired
  private ContextUtils contextUtils;

  @Override
  public Page<OfferApplicationDto> getOffersApplications(Authentication auth, PaginationQueriesDto queries) {
    UtemUser user = contextUtils.getUserFromContext(auth);
    Pageable page = PageUtils.getPageable(queries);

    Page<OfferApplication> offerApplications;
    if(user.getRole() == UtemRoles.ROLE_COMPANY) {
      offerApplications = offerApplicationRepository.findAllByOffer_OfferAuthor_Company(user.getCompany(), page);
    } else if (user.getRole() == UtemRoles.ROLE_USER) {
      offerApplications = offerApplicationRepository.findAllByUser(user, page);
    } else {
      offerApplications = offerApplicationRepository.findAll(page);
    }

    return offerApplications.map(offerApplication -> new OfferApplicationDto().toDto(offerApplication));
  }

  @Override
  public OfferApplicationDto getOfferApplicationsById(Authentication auth, Long id) {
    UtemUser user = contextUtils.getUserFromContext(auth);
    OfferApplication offerApplication = offerApplicationRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);

    if(user.getRole() == UtemRoles.ROLE_COMPANY && !offerApplication.getOffer().getOfferAuthor().getCompany().equals(user.getCompany())) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    } else if (user.getRole() == UtemRoles.ROLE_USER && !offerApplication.getUser().equals(user)) {
      throw new ValidationException("invalid user: user did not made the requested resource");
    }

    return new OfferApplicationDto().toDto(offerApplication);
  }

  @Override
  public OfferApplicationDto createOfferApplication(Authentication auth, OfferApplicationRequest request) {
    UtemUser user = contextUtils.getUserFromContext(auth);
    // Validations

    // Handle null using exception
    Offer offer = offerRepository.findById(request.getOfferId()).orElse(null);

    OfferApplication offerApplication = new OfferApplication();
    // Get user from context when context is available
      offerApplication.setUser(user);
      offerApplication.setOffer(offer);
      offerApplication.setRequestDate(LocalDateTime.now());
      offerApplication.setOfferApplicationStatus(OfferApplicationStatus.UNSEEN);

    return new OfferApplicationDto().toDto(offerApplicationRepository.save(offerApplication));
  }

  @Override
  public OfferApplicationDto updateOfferApplication(Authentication auth, OfferApplicationRequest request, Long id) {

    // Validations



    // logic
    OfferApplication offer = offerApplicationRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    offer.setOfferApplicationStatus(request.getOfferApplicationStatus());

    return new OfferApplicationDto().toDto(offerApplicationRepository.save(offer));
  }

  @Override
  public Type deleteRequest(Authentication auth, Long id) throws Exception {

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
