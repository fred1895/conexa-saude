package br.com.conexa.hospital.infra.jpa;

import br.com.conexa.hospital.domain.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorJpa extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);
}
