package com.rober.hotel.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rober.hotel.Models.Reservas;

import java.util.List;

public interface ReservaInterface extends JpaRepository<Reservas, Integer> {
    List<Reservas> findByActivoTrue();
}

