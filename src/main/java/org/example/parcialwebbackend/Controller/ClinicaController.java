package org.example.parcialwebbackend.Controller;

import jakarta.validation.Valid;
import org.example.parcialwebbackend.Dtos.ClinicaDto;
import org.example.parcialwebbackend.Service.ClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicas")
@CrossOrigin(origins = "*")
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping
    public ResponseEntity<List<ClinicaDto>> obtenerTodas() {
        List<ClinicaDto> clinicas = clinicaService.obtenerTodas();
        return ResponseEntity.ok(clinicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDto> obtenerPorId(@PathVariable Long id) {
        ClinicaDto clinica = clinicaService.obtenerPorId(id);
        return ResponseEntity.ok(clinica);
    }

    @PostMapping
    public ResponseEntity<ClinicaDto> crear(@Valid @RequestBody ClinicaDto clinicaDto) {
        ClinicaDto clinicaCreada = clinicaService.crear(clinicaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClinicaDto clinicaDto) {
        ClinicaDto clinicaActualizada = clinicaService.actualizar(id, clinicaDto);
        return ResponseEntity.ok(clinicaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clinicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ClinicaDto>> buscarPorNombre(@RequestParam String nombre) {
        List<ClinicaDto> clinicas = clinicaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(clinicas);
    }

    @GetMapping("/buscar/ciudad")
    public ResponseEntity<List<ClinicaDto>> buscarPorCiudad(@RequestParam String ciudad) {
        List<ClinicaDto> clinicas = clinicaService.buscarPorCiudad(ciudad);
        return ResponseEntity.ok(clinicas);
    }
}