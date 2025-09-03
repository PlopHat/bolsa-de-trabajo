package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CompanyRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CompanyResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface CompanyService {
  Page<CompanyResponseDto> getCompanies(Authentication auth, PaginationQueriesDto queries);

  CompanyResponseDto getCompany(Authentication auth, String rut);

  CompanyResponseDto createCompany(Authentication auth, @Valid CompanyRequestDto req);

    CompanyResponseDto deleteCompany(String id);
}
