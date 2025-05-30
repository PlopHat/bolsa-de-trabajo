package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import cl.utem.bolsadetrabajo_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "")
  public ResponseEntity<List<UserResponse>> getAllUsers() {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<UserResponse> getUserById(
          @PathVariable Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
  }

}
