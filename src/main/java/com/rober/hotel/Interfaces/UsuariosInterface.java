package com.rober.hotel.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rober.hotel.Models.Users.Usuarios;

public interface UsuariosInterface extends JpaRepository<Usuarios, Integer> {
  UserDetails findByLogin(String username);
}
