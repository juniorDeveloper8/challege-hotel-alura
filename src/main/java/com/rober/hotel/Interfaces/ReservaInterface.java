package com.rober.hotel.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rober.hotel.Models.Reservas;

public interface ReservaInterface extends JpaRepository<Reservas, Integer> {
    Page<Reservas> findByActivoTrue(Pageable paginacion);
}

