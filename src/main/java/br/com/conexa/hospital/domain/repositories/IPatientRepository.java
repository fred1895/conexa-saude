package br.com.conexa.hospital.domain.repositories;

import br.com.conexa.hospital.domain.entities.Patient;

import java.util.List;

public interface IPatientRepository {

    void save(Patient patient);

    Patient findById(Long id);

    List<Patient> getPatients();

    void delete(Long id);
}
