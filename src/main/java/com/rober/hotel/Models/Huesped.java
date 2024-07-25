package com.rober.hotel.Models;

import java.sql.Date;

import com.rober.hotel.DTO.DatosActualizarHuesped;
import com.rober.hotel.DTO.DatosRegistroHuesped;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "huespedes")
@Entity(name = "huespede")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String ape;
    private String phone;
    private Date fechaN;
    private String documento;

    @Column(columnDefinition = "TINYINT(1)" )
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Nacionalidad nacionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_re")
    private Reservas reservas;

    // save
    public Huesped(DatosRegistroHuesped datosRegistroHuesped) {
        this.activo = true;
        this.nom = datosRegistroHuesped.nom();
        this.ape = datosRegistroHuesped.ape();
        this.phone = datosRegistroHuesped.phone();
        this.fechaN = datosRegistroHuesped.fechaN();
        this.documento = datosRegistroHuesped.documento();
        this.nacionalidad = datosRegistroHuesped.nacionalidad();
    }

    // actualizar
    public void actualizarDatos(DatosActualizarHuesped datosActualizarHuesped) {
        if (datosActualizarHuesped.nom() != null) {
            this.nom = datosActualizarHuesped.nom();
        }
        if (datosActualizarHuesped.ape() != null) {
            this.ape = datosActualizarHuesped.ape();
        }
    }

    // eliminar
    public void desactivarHuesped() {
        this.activo = false;
    }
}
