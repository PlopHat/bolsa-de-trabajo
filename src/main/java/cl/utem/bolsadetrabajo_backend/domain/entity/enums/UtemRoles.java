package cl.utem.bolsadetrabajo_backend.domain.entity.enums;

public enum UtemRoles {
  USER,
  COMPANY,
  ADMINISTRATOR;

  public String getRoleName() {
    return "ROLE_" + this.name();
  }

}
