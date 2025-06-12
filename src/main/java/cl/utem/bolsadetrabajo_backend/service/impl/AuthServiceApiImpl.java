package cl.utem.bolsadetrabajo_backend.service.impl;

import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthLoginRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.request.AuthRegisterRequest;
import cl.utem.bolsadetrabajo_backend.api.dto.response.AuthResponse;
import cl.utem.bolsadetrabajo_backend.configuration.CustomUserDetailsObject;
import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import cl.utem.bolsadetrabajo_backend.repository.UtemUserRepository;
import cl.utem.bolsadetrabajo_backend.service.AuthService;
import cl.utem.bolsadetrabajo_backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceApiImpl implements AuthService {

  @Autowired
  private UtemUserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UtemUserRepository utemUserRepository;

  @Override
  public AuthResponse login(AuthLoginRequest request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );

      UtemUser user = userRepository.findByEmail(request.getEmail().toLowerCase())
              .orElseThrow();

      String jwtToken = jwtService.generateToken(new CustomUserDetailsObject(user));

      return AuthResponse.builder()
              .token(jwtToken)
              .build();
    }

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
      UtemUser newUser =  new UtemUser();
        newUser.setEmail(request.getEmail().toLowerCase());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setName(request.getName());
        newUser.setRole(UtemRoles.ROLE_USER);

      String jwtToken = jwtService.generateToken(new CustomUserDetailsObject(utemUserRepository.save(newUser)));

      return AuthResponse.builder()
              .token(jwtToken)
              .build();
    }
}
