package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.UserRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.Company;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.repository.CompanyRepository;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceApiImpl implements UserService {

  @Autowired
  private UtemUserRepository userRepository;
  @Autowired
  private CompanyRepository companyRepository;

  @Override
  public List<UserResponse> getAllUsers() {
    List<UtemUser> users = userRepository.findAll();
    List<UserResponse> userResponses = new ArrayList<>();
    for (UtemUser user : users) {
      userResponses.add(new UserResponse().toDto(user));
    }

    return userResponses;
  }

  @Override
  public UserResponse getUserById(Long id) {

    return new UserResponse().toDto(userRepository.getUserById(id));
  }

  @Override
  public UserResponse createUser(Authentication auth, @Valid UserRequest req) {

    Company company = companyRepository.getCompanyByRut(req.getCompanyRut());

    UtemUser user = new UtemUser();
    user.setName(req.getName());
    user.setLastname(req.getLastName());
    user.setEmail(req.getEmail());
    user.setPassword(req.getPassword());
    user.setRole(req.getRole());
    if(company != null) {
      user.setCompany(company);
    }

    UtemUser savedUser = userRepository.save(user);

    return new UserResponse().toDto(savedUser);
  }


}
