package br.com.conexa.hospital.domain.adapters;

import br.com.conexa.hospital.domain.entities.Doctor;

public interface IDoctorService {
    Doctor findByEmail(String username);
}
