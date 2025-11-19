package org.example.parcialwebbackend.Controller;

import jakarta.validation.Valid;
import org.example.parcialwebbackend.Dtos.DoctorDto;
import org.example.parcialwebbackend.Service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> obtenerTodos() {
        List<DoctorDto> doctores = doctorService.obtenerTodos();
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> obtenerPorId(@PathVariable Long id) {
        DoctorDto doctor = doctorService.obtenerPorId(id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<DoctorDto> crear(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto doctorCreado = doctorService.crear(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto doctorActualizado = doctorService.actualizar(id, doctorDto);
        return ResponseEntity.ok(doctorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        doctorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinica/{clinicaId}")
    public ResponseEntity<List<DoctorDto>> obtenerPorClinica(@PathVariable Long clinicaId) {
        List<DoctorDto> doctores = doctorService.obtenerPorClinica(clinicaId);
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<DoctorDto>> obtenerPorEspecialidad(@RequestParam String especialidad) {
        List<DoctorDto> doctores = doctorService.obtenerPorEspecialidad(especialidad);
        return ResponseEntity.ok(doctores);
    }
}