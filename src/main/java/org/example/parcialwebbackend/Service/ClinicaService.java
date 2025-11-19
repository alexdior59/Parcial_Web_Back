package org.example.parcialwebbackend.Service;

import org.example.parcialwebbackend.Dtos.ClinicaDto;
import org.example.parcialwebbackend.Mapper.ClinicaMapper;
import org.example.parcialwebbackend.Model.Clinica;
import org.example.parcialwebbackend.Repository.ClinicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;
    private final ClinicaMapper clinicaMapper;

    public ClinicaService(ClinicaRepository clinicaRepository, ClinicaMapper clinicaMapper) {
        this.clinicaRepository = clinicaRepository;
        this.clinicaMapper = clinicaMapper;
    }

    public List<ClinicaDto> obtenerTodas() {
        return clinicaRepository.findAll()
                .stream()
                .map(clinicaMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClinicaDto obtenerPorId(Long id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clínica no encontrada con ID: " + id));
        return clinicaMapper.toDto(clinica);
    }

    public ClinicaDto crear(ClinicaDto clinicaDto) {
        Clinica clinica = clinicaMapper.toEntity(clinicaDto);

        if (clinica.getFechaCreacion() == null) {
            clinica.setFechaCreacion(LocalDate.now());
        }

        Clinica clinicaGuardada = clinicaRepository.save(clinica);
        return clinicaMapper.toDto(clinicaGuardada);
    }

    public ClinicaDto actualizar(Long id, ClinicaDto clinicaDto) {
        Clinica clinicaExistente = clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clínica no encontrada con ID: " + id));

        clinicaExistente.setNombre(clinicaDto.getNombre());
        clinicaExistente.setDireccion(clinicaDto.getDireccion());
        clinicaExistente.setCantidadCamas(clinicaDto.getCantidadCamas());
        clinicaExistente.setTelefono(clinicaDto.getTelefono());
        clinicaExistente.setCiudad(clinicaDto.getCiudad());

        Clinica clinicaActualizada = clinicaRepository.save(clinicaExistente);
        return clinicaMapper.toDto(clinicaActualizada);
    }

    public void eliminar(Long id) {
        if (!clinicaRepository.existsById(id)) {
            throw new RuntimeException("Clínica no encontrada con ID: " + id);
        }
        clinicaRepository.deleteById(id);
    }

    public List<ClinicaDto> buscarPorNombre(String nombre) {
        return clinicaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(clinicaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClinicaDto> buscarPorCiudad(String ciudad) {
        return clinicaRepository.findByCiudad(ciudad)
                .stream()
                .map(clinicaMapper::toDto)
                .collect(Collectors.toList());
    }
}