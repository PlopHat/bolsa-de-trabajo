package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.request.CompanyRequestDto;
import cl.utem.bolsadetrabajo_backend.api.dto.request.PaginationQueriesDto;
import cl.utem.bolsadetrabajo_backend.api.dto.response.CompanyResponseDto;
import cl.utem.bolsadetrabajo_backend.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Compañías",
        description = "API para el control de compañías"
)
@RestController
@RequestMapping(value = "api/companies")
public class CompaniesController {

  @Autowired
  private CompanyService companyService;

  @GetMapping(value = "")
  public ResponseEntity<Page<CompanyResponseDto>> getCompanies(
          Authentication auth,
          @ModelAttribute PaginationQueriesDto queries
          ) {

    return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanies(auth, queries));
  }

  @GetMapping(value = "{rut}")
  public ResponseEntity<CompanyResponseDto> getCompany(
          Authentication auth,
          @PathVariable("rut") String id
  ) {


    return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(auth, id));
  }

  @PostMapping(value = "")
  public ResponseEntity<CompanyResponseDto> createCompany(
          Authentication auth,
          @RequestBody CompanyRequestDto req
  ) {

    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(auth, req));
  }

  @PutMapping(value = "")
  public ResponseEntity<CompanyResponseDto> updateCompany(
          Authentication auth,
          @RequestBody CompanyRequestDto req
  ) {

    return null;
  }

    @PreAuthorize(
            "hasAuthority(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ROLE_ADMINISTRATOR.name())"
    )
    @DeleteMapping(value = "{rut}")
    public ResponseEntity<CompanyResponseDto> deleteCompany(
          @PathVariable("rut") String rut
    ) {
    return ResponseEntity.status(HttpStatus.OK).body(companyService.deleteCompany(rut));
    }

}
