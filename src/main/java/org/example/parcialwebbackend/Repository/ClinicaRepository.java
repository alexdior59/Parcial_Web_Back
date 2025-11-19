
package org.example.parcialwebbackend.Repository;

import org.example.parcialwebbackend.Model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    List<Clinica> findByNombreContainingIgnoreCase(String nombre);

    List<Clinica> findByCiudad(String ciudad);
}