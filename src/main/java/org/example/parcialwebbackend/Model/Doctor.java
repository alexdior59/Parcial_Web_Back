package org.example.parcialwebbackend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "doctores")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especialidad;

    @Column(name = "clinica_id")
    private Long clinicaId;

    private String email;

    private String telefono;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
}