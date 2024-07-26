package com.rober.hotel.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rober.hotel.Models.Huesped;

import java.util.List;

public interface HuespedInterface extends JpaRepository<Huesped, Integer> {
    List<Huesped> findByActivoTrue();
}
