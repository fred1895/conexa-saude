package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.domain.adapters.IPatientService;
import br.com.conexa.hospital.domain.dto.PatientDto;
import br.com.conexa.hospital.domain.entities.Patient;
import br.com.conexa.hospital.domain.entities.Schedule;
import br.com.conexa.hospital.domain.repositories.IPatientRepository;
import br.com.conexa.hospital.infra.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository repository;
    private final ScheduleRepository scheduleRepository;

    public PatientService(IPatientRepository repository, ScheduleRepository scheduleRepository) {
        this.repository = repository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void save(Patient patient) {
        repository.save(patient);
    }

    @Override
    public void saveFromDto(PatientDto dto) {
        System.out.println("DTO: " + dto);
        Patient patient = new Patient();
        patient.setPhone(dto.getPhone());
        patient.setName(dto.getName());
        patient.setCpf(dto.getCpf());
        patient.setAge(dto.getAge());
        System.out.println(patient);
        save(patient);
    }

    @Override
    public Patient findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public PatientDto findDtoById(Long id) {
        Patient patient = repository.findById(id);
        return new PatientDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = repository.getPatients();
        return patients.stream().map(PatientDto::new).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, PatientDto patient) {
        Patient patientDb = this.findById(id);
        patientDb.setAge(patient.getAge());
        patientDb.setCpf(patient.getCpf());
        patientDb.setName(patient.getName());
        patientDb.setPhone(patient.getPhone());

        save(patientDb);
    }

    @Override
    public void delete(Long id) {
        Patient patient = findById(id);
        List<Schedule> schedules = patient.getSchedules();
        schedules.forEach(scheduleRepository::delete);
        repository.delete(id);
    }

}
