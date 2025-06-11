package cl.utem.bolsadetrabajo_backend.service;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;

import java.time.LocalDateTime;

public interface ValidationService {

  public UtemUser verifyUser(String username) throws ValidationException;

}
