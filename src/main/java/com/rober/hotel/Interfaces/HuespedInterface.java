package com.rober.hotel.Interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rober.hotel.Models.Huesped;

public interface HuespedInterface extends JpaRepository<Huesped, Integer> {
    Page<Huesped> findByActivoTrue(Pageable paginacion);
}
