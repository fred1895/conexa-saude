package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.domain.dto.PatientDto;
import br.com.conexa.hospital.domain.entities.Patient;
import br.com.conexa.hospital.domain.repositories.IPatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
class PatientServiceTest {

    @Mock
    private IPatientRepository repository;

    @InjectMocks
    private PatientService service;

    @BeforeEach
    void setUp() {
        Patient patient = getPatient();
        BDDMockito.when(repository.findById(ArgumentMatchers.any(Long.class))).thenReturn(patient);
        BDDMockito.when(repository.getPatients()).thenReturn(Arrays.asList(patient));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void saveFromDto() {
    }

    @Test
    void findById() {
        Patient patientCompared = getPatient();
        PatientDto dto = service.findDtoById(1L);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getName(), patientCompared.getName());
    }

    @Test
    void getAllPatients() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    Patient getPatient() {
        Patient patient = new Patient();
        patient.setName("fred");
        patient.setAge("23");
        patient.setCpf("17048212748");
        patient.setPhone("22988496738");
        return patient;
    }
}