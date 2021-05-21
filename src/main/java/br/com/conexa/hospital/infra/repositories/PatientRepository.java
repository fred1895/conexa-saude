package br.com.conexa.hospital.infra.repositories;

import br.com.conexa.hospital.api.exceptions.ObjectNotFoundException;
import br.com.conexa.hospital.domain.entities.Patient;
import br.com.conexa.hospital.domain.repositories.IPatientRepository;
import br.com.conexa.hospital.infra.jpa.PatientJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRepository implements IPatientRepository {

    private final PatientJpa jpa;

    public PatientRepository(PatientJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(Patient patient) {
        jpa.save(patient);
    }

    @Override
    public Patient findById(Long id) {
        Patient patient = jpa.findById(id).orElseThrow(() -> new ObjectNotFoundException("Paciente nao encontrado"));
        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        return jpa.findAll();
    }

    @Override
    public void delete(Long id) {
        Patient patient = findById(id);
        jpa.delete(patient);
    }
}
