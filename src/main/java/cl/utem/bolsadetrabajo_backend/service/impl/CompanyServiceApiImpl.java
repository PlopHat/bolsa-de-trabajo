package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CompanyRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CompanyResponseDto;
import cl.utem.bolsadetrabajo_backend.domain.entity.Company;
import cl.utem.bolsadetrabajo_backend.repository.CompanyRepository;
import cl.utem.bolsadetrabajo_backend.service.CompanyService;
import cl.utem.bolsadetrabajo_backend.utils.PageUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceApiImpl implements CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  @Override
  public Page<CompanyResponseDto> getCompanies(Authentication auth, PaginationQueriesDto queries) {
    Pageable page = PageUtils.getPageable(queries);

    Page<Company> company = companyRepository.findAll(page);

    return company.map(companyToDto -> new CompanyResponseDto().toDto(companyToDto));
  }

  @Override
  public CompanyResponseDto getCompany(Authentication auth, String rut) {
    Company company = companyRepository.getCompanyByRut(rut);

    return new CompanyResponseDto().toDto(company);
  }

  @Override
  public CompanyResponseDto createCompany(Authentication auth, @Valid CompanyRequestDto req) {
    Company company = new Company();

    company.setRut(req.getRut());
    company.setFantasyName(req.getFantasyName());

    return new CompanyResponseDto().toDto(companyRepository.save(company));
  }

}
