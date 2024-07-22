package com.rober.hotel.Models.Users;

import java.util.Collection;
import java.util.List;

import com.rober.hotel.DTO.DTOUSER.DatosRegistroUserLogin;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "usuario")
@Entity(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder

public class Usuarios implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String login;
  private String clave;
  private boolean activo;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return clave;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return activo;
  }

  public Usuarios(DatosRegistroUserLogin datosRegistroUserLogin) {
    this.activo = true;
    this.login = datosRegistroUserLogin.login();

    // Encriptar la contraseña utilizando BCryptPasswordEncoder
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.clave = passwordEncoder.encode(datosRegistroUserLogin.clave());
  }
}