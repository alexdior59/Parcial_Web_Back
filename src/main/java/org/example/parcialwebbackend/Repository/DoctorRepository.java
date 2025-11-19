package org.example.parcialwebbackend.Repository;

import org.example.parcialwebbackend.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByClinicaId(Long clinicaId);

    List<Doctor> findByEspecialidad(String especialidad);
}