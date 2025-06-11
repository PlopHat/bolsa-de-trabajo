package cl.utem.bolsadetrabajo_backend.api.controller;

import cl.utem.bolsadetrabajo_backend.api.dto.response.UserResponse;
import cl.utem.bolsadetrabajo_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ADMINISTRATOR.name())")
  public ResponseEntity<List<UserResponse>> getAllUsers() {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping(value = "{id}")
  @PreAuthorize(value =
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).USER.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).ADMINISTRATOR.name()) or " +
          "hasRole(T(cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles).COMPANY.name())")
  public ResponseEntity<UserResponse> getUserById(
          @PathVariable Long id) {

    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
  }

}
