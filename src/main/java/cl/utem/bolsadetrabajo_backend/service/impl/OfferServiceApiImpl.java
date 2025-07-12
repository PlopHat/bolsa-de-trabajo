package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferApplication;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferLocation;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import cl.utem.bolsadetrabajo_backend.repository.OfferApplicationRepository;
import cl.utem.bolsadetrabajo_backend.repository.OfferLocationRepository;
import cl.utem.bolsadetrabajo_backend.repository.OfferRepository;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
import cl.utem.bolsadetrabajo_backend.utils.ContextUtils;
import cl.utem.bolsadetrabajo_backend.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceApiImpl implements OfferService {

  @Autowired
  private OfferRepository offerRepository;

  @Autowired
  private UtemUserRepository utemUserRepository;

  @Autowired
  private ContextUtils contextUtils;
  @Autowired
  private OfferLocationRepository offerLocationRepository;
  @Autowired
  private OfferApplicationRepository offerApplicationRepository;

  @Override
  public Page<OfferResponse> getOffers(Authentication auth, PaginationQueriesDto queries) {

    // get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);

    Pageable pageable = PageUtils.getPageable(queries);
    Page<Offer> offers;
    offers = offerRepository.findAll(pageable);

    return offers.map(offer -> new OfferResponse().toDto(offer));
  }

  @Override
  public OfferResponse getOfferById(Authentication auth, Long id) {
    Offer offer = offerRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    // Logic
    return new OfferResponse().toDto(offer);
  }

  @Override
  public OfferApplicationDto getOfferApplicationById(Authentication auth, Long offerId, Long userId) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);

    // Validate if user is authorized to view the offer application
    if (user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && !user.getId().equals(userId)) {
      throw new ValidationException("Access denied: User is not authorized to view this offer application");
    }

    // Fetch the offer application by offerId and userId
    OfferApplication offerApplication = offerApplicationRepository.getOfferApplicationByOfferIdAndUserId(offerId, userId)
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer application not found"));

    return new OfferApplicationDto().toDto(offerApplication);
  }

  @Override
  public OfferResponse updateOffer(Authentication auth, Long id, OfferRequestDto req) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);
    Offer offer = offerRepository.findById(id)
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer not found"));
    UtemUser offerAuthor = utemUserRepository.findById(req.getOfferAuthorId())
            .orElseThrow(() -> new CustomEntityNotFoundException("Offer author not found"));
    OfferLocation offerLocation = offerLocationRepository.findById(req.getOfferLocationId())
            .orElse(null);

    // Validations
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && user.getCompany() != offerAuthor.getCompany()) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

    // Logic per value with if statements
    if(req.getOfferName() != null) {
      offer.setOfferName(req.getOfferName());
    }
    if(req.getOfferDescription() != null) {
      offer.setOfferDescription(req.getOfferDescription());
    }
    if(req.getStartDate() != null) {
      offer.setStartDate(req.getStartDate());
    }
    if(req.getEndDate() != null) {
      offer.setEndDate(req.getEndDate());
    }
    if(req.getSalary() != null) {
      offer.setSalary(req.getSalary());
    }
    if(req.getWorkType() != null) {
      offer.setWorkType(req.getWorkType());
    }
    if(req.getWorkMode() != null) {
      offer.setWorkMode(req.getWorkMode());
    }
    if(req.getOfferLocationId() != null) {
      offer.setOfferLocation(offerLocation);
    }
    // Save and return

    return new OfferResponse().toDto(offerRepository.save(offer));
  }

  @Override
  public OfferResponse createOffer(Authentication auth, OfferRequestDto req) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);

    if(user.getCompany() == null && user.getRole() != UtemRoles.ROLE_ADMINISTRATOR) {
      throw new ValidationException("invalid company: user does not have company");
    }

    // Validations
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && user.getCompany() != user.getCompany()) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

    OfferLocation offerLocation = offerLocationRepository.findById(req.getOfferLocationId())
        .orElseThrow(() -> new CustomEntityNotFoundException("Location not found"));

    // Logic
    Offer offer = new Offer();
      offer.setOfferName(req.getOfferName());
      offer.setOfferDescription(req.getOfferDescription());
      offer.setStartDate(req.getStartDate());
      offer.setEndDate(req.getEndDate());
      offer.setSalary(req.getSalary());
      offer.setWorkType(req.getWorkType());
      offer.setWorkMode(req.getWorkMode());
      offer.setOfferLocation(offerLocation);
      offer.setOfferAuthor(user);

    return new OfferResponse().toDto(offerRepository.save(offer));
  }

  @Override
  public OfferResponse deleteOffer(Authentication auth, Long id) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);
    Offer offer = offerRepository.findById(id)
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer not found"));

    // Validations
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && offer.getOfferAuthor().getCompany() != user.getCompany() ) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

    // Logic
    offerRepository.delete(offer);
    return new OfferResponse().toDto(offer);
  }

  @Override
  public Page<OfferApplicationDto> getOfferApplications(Authentication auth, Long offerId, PaginationQueriesDto queries) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);

    Offer offer = offerRepository.findById(offerId)
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer not found"));

    // Validate if user is authorized to view the offer applications
    if (user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && offer.getOfferAuthor().getCompany() != user.getCompany() ) {
      throw new ValidationException("Access denied: User is not authorized to view this offer applications");
    }

    Pageable pageable = PageUtils.getPageable(queries);
    Page<OfferApplication> offerApplications = offerApplicationRepository.findAllByOfferId(offerId, pageable);

    return offerApplications.map(offerApplication -> new OfferApplicationDto().toDto(offerApplication));

  }

}
