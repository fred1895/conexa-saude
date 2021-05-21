package br.com.conexa.hospital.infra.jpa;

import br.com.conexa.hospital.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpa extends JpaRepository<Patient, Long> {

}
