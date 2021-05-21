package br.com.conexa.hospital.infra.repositories;

import br.com.conexa.hospital.api.exceptions.ObjectNotFoundException;
import br.com.conexa.hospital.domain.entities.Doctor;
import br.com.conexa.hospital.domain.repositories.IDoctorRepository;
import br.com.conexa.hospital.infra.jpa.DoctorJpa;
import org.springframework.stereotype.Service;

@Service
public class DoctorRepository implements IDoctorRepository {

    private final DoctorJpa jpa;

    public DoctorRepository(DoctorJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Doctor findByEmail(String email) {
        return jpa.findByEmail(email);
    }

    @Override
    public Doctor findById(Long id) {
        return jpa.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Medico nao cadastrado no banco de dados")
        );
    }
}
