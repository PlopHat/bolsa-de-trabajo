package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.OfferApplicationRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.OfferApplicationDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

public interface OfferApplicationService {

  public List<OfferApplicationDto> getOffers();
  public OfferApplicationDto getOfferById(Long id);
  public OfferApplicationDto createRequest(OfferApplicationRequest request);

  OfferApplicationDto updateRequest(OfferApplicationRequest request, Long id);

  Type deleteRequest(Long id) throws Exception;
}
