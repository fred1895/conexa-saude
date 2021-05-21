package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.domain.adapters.IDoctorService;
import br.com.conexa.hospital.domain.entities.Doctor;
import br.com.conexa.hospital.domain.repositories.IDoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository repository;

    public DoctorService(IDoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Doctor findByEmail(String username) {
        return repository.findByEmail(username);
    }
}
