package cl.utem.bolsadetrabajo_backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {
  private String name;

}
