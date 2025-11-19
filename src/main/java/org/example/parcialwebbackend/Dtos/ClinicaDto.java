package org.example.parcialwebbackend.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicaDto {

    private Long identificador;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotNull(message = "La cantidad de camas es obligatoria")
    private Integer cantidadCamas;

    private String telefono;

    private String ciudad;

    private LocalDate fechaCreacion;
}