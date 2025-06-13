package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.domain.exception.types.CustomEntityNotFoundException;
import cl.utem.bolsadetrabajo_backend.domain.exception.types.ValidationException;
import cl.utem.bolsadetrabajo_backend.utils.ContextUtils;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.Offer;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.repository.OfferRepository;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.OfferService;
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
      // Needs investigation on how it works, for now, we will get the user company from mail (SECURITY ISSUE)
      offers = offerRepository.findAllByOfferAuthor_Company(user.getCompany(), pageable);
    }

    return offers.map(offer -> new OfferResponse().toDto(offer));
  }

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

}
