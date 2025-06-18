package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.OfferLocation;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
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

  @Override
  public Page<OfferResponse> getOffers(Authentication auth, PaginationQueriesDto queries) {

    // get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);

    Pageable pageable = PageUtils.getPageable(queries);
    Page<Offer> offers;
    if(user.getRole() == UtemRoles.ROLE_ADMINISTRATOR) {
      offers = offerRepository.findAll(pageable);
    } else {
      // FindAllByCompany(of user in context)
      offers = offerRepository.findAllByOfferAuthor_Company(user.getCompany(), pageable);
    }

    return offers.map(offer -> new OfferResponse().toDto(offer));
  }

  @Override
  public OfferResponse getOfferById(Authentication auth, Long id) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);
    // Validations
    Offer offer = offerRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    // this validation is only valid if not Administrator
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && user.getCompany() != offer.getOfferAuthor().getCompany()) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

    // Logic
    return new OfferResponse().toDto(offerRepository.getOfferById(id));
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
    UtemUser offerAuthor = utemUserRepository.findById(req.getOfferAuthorId())
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer author not found"));
    OfferLocation offerLocation = offerLocationRepository.findById(req.getOfferLocationId())
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer location not found"));

    // Validations
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && user.getCompany() != offerAuthor.getCompany()) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

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
      offer.setOfferAuthor(offerAuthor);

    return new OfferResponse().toDto(offerRepository.save(offer));
  }

  @Override
  public OfferResponse deleteOffer(Authentication auth, Long id) {
    // Get User from Authentication
    UtemUser user = contextUtils.getUserFromContext(auth);
    Offer offer = offerRepository.findById(id)
        .orElseThrow(() -> new CustomEntityNotFoundException("Offer not found"));

    // Validations
    if(user.getRole() != UtemRoles.ROLE_ADMINISTRATOR && offer.getOfferAuthor() != user ) {
      throw new ValidationException("invalid company: user does not share the same company as requested resource");
    }

    // Logic
    offerRepository.delete(offer);
    return new OfferResponse().toDto(offer);
  }
}
