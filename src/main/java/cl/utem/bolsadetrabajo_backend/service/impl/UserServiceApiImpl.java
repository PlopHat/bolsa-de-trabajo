package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceApiImpl implements UserService {

  @Autowired
  private UtemUserRepository userRepository;

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
}
