package cl.utem.bolsadetrabajo_backend.configuration;

import cl.utem.bolsadetrabajo_backend.domain.entity.UtemUser;
import cl.utem.bolsadetrabajo_backend.domain.entity.enums.UtemRoles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetailsObject implements UserDetails {

  private String username;
  private String password;
  private UtemRoles role;
  private Long id;
  private String name;
  private String lastname;
  private List<GrantedAuthority> authorities;

  public CustomUserDetailsObject(UtemUser user) {
    this.username = user.getEmail();
    this.password = user.getPassword();
    this.id = user.getId();
    this.role = user.getRole();
    this.name = user.getName();
    this.lastname = user.getLastname();
    this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }

}
