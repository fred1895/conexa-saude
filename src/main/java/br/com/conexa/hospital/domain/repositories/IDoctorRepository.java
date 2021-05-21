package br.com.conexa.hospital.domain.repositories;

import br.com.conexa.hospital.domain.entities.Doctor;

public interface IDoctorRepository {
    Doctor findByEmail(String email);

    Doctor findById(Long id);
}
