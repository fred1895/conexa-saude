package br.com.conexa.hospital.domain.adapters;

import br.com.conexa.hospital.domain.dto.PatientDto;
import br.com.conexa.hospital.domain.entities.Patient;

import java.util.List;

public interface IPatientService {
    void save(Patient patient);

    void saveFromDto(PatientDto dto);

    Patient findById(Long id);

    PatientDto findDtoById(Long id);

    List<PatientDto> getAllPatients();

    void update(Long id, PatientDto patient);

    void delete(Long id);
}
