package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.config.security.CustomUserDetail;
import br.com.conexa.hospital.domain.dto.ScheduleDto;
import br.com.conexa.hospital.domain.entities.Doctor;
import br.com.conexa.hospital.domain.entities.Patient;
import br.com.conexa.hospital.domain.entities.Schedule;
import br.com.conexa.hospital.domain.repositories.IScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements br.com.conexa.hospital.domain.adapters.IScheduleService {

    private final IScheduleRepository repository;
    private final PatientService patientService;

    public ScheduleService(IScheduleRepository repository, PatientService patientService) {
        this.repository = repository;
        this.patientService = patientService;
    }

    @Override
    public void saveFromDto(ScheduleDto dto) {
        CustomUserDetail authenticated = UserService.authenticated();
        Doctor doctor = authenticated.getDoctor();

        Patient patient = patientService.findById(dto.getPatientId());

        Schedule schedule = new Schedule();
        schedule.setDoctor(doctor);
        schedule.setPatient(patient);
        schedule.setTime(dto.getTime());

        repository.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        repository.delete(schedule);
    }
}
