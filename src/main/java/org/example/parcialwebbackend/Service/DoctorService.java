package org.example.parcialwebbackend.Service;

import org.example.parcialwebbackend.Dtos.DoctorDto;
import org.example.parcialwebbackend.Mapper.DoctorMapper;
import org.example.parcialwebbackend.Model.Doctor;
import org.example.parcialwebbackend.Repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorDto> obtenerTodos() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public DoctorDto obtenerPorId(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));
        return doctorMapper.toDto(doctor);
    }

    public DoctorDto crear(DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);

        if (doctor.getFechaContratacion() == null) {
            doctor.setFechaContratacion(LocalDate.now());
        }

        Doctor doctorGuardado = doctorRepository.save(doctor);
        return doctorMapper.toDto(doctorGuardado);
    }

    public DoctorDto actualizar(Long id, DoctorDto doctorDto) {
        Doctor doctorExistente = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + id));

        doctorExistente.setNombre(doctorDto.getNombre());
        doctorExistente.setEspecialidad(doctorDto.getEspecialidad());
        doctorExistente.setClinicaId(doctorDto.getClinicaId());
        doctorExistente.setEmail(doctorDto.getEmail());
        doctorExistente.setTelefono(doctorDto.getTelefono());

        Doctor doctorActualizado = doctorRepository.save(doctorExistente);
        return doctorMapper.toDto(doctorActualizado);
    }

    public void eliminar(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor no encontrado con ID: " + id);
        }
        doctorRepository.deleteById(id);
    }

    public List<DoctorDto> obtenerPorClinica(Long clinicaId) {
        return doctorRepository.findByClinicaId(clinicaId)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> obtenerPorEspecialidad(String especialidad) {
        return doctorRepository.findByEspecialidad(especialidad)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }
}