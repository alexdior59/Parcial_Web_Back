package org.example.parcialwebbackend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clinicas")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(name = "cantidad_camas")
    private Integer cantidadCamas;

    private String telefono;

    private String ciudad;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}