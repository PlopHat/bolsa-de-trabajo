package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  public List<UserResponse> getAllUsers();
  public UserResponse getUserById(Long id);
}
