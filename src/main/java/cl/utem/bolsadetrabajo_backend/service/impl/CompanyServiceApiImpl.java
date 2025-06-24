package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CompanyRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CompanyDto;
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
  public Page<CompanyDto> getCompanies(Authentication auth, PaginationQueriesDto queries) {
    Pageable page = PageUtils.getPageable(queries);

    Page<Company> company = companyRepository.findAll(page);

    return company.map(companyToDto -> new CompanyDto().toDto(companyToDto));
  }

  @Override
  public CompanyDto getCompany(Authentication auth, int rut) {
    Company company = companyRepository.getCompanyByRut(rut);

    return new CompanyDto().toDto(company);
  }

  @Override
  public CompanyDto createCompany(Authentication auth, @Valid CompanyRequestDto req) {
    Company company = new Company();

    company.setRut(req.getRut());
    company.setFantasyName(req.getFantasyName());

    return new CompanyDto().toDto(companyRepository.save(company));
  }

}
