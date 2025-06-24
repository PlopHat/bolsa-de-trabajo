package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CompanyRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CompanyDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface CompanyService {
  Page<CompanyDto> getCompanies(Authentication auth, PaginationQueriesDto queries);

  CompanyDto getCompany(Authentication auth, int rut);

  CompanyDto createCompany(Authentication auth, @Valid CompanyRequestDto req);
}
