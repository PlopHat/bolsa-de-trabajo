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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    Sort sort = null;
    if(queries.getSortBy()!=null) {
      Sort.Direction direction = queries.getSortDirection() != null && queries.getSortDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      sort = Sort.by(direction, queries.getSortBy());
    }

    Pageable pageable = sort != null ?
            PageRequest.of(queries.getPage(), queries.getSize(), sort) : PageRequest.of(queries.getPage(), queries.getSize());

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
    if(user.getCompany() != offer.getOfferAuthor().getCompany()) {
      throw new ValidationException();
    }

    // Logic

    return new OfferResponse().toDto(offerRepository.getOfferById(id));
  }

}
