package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Type;

public interface OfferApplicationService {

  public Page<OfferApplicationDto> getOffersApplications(Authentication auth, PaginationQueriesDto queries);
  public OfferApplicationDto getOfferApplicationsById(Authentication auth, Long id);
  public OfferApplicationDto createOfferApplication(Authentication auth, OfferApplicationRequest request);

  public OfferApplicationDto updateOfferApplication(Authentication auth, OfferApplicationRequest request, Long id);

  public Type deleteRequest(Authentication auth, Long id) throws Exception;
}
