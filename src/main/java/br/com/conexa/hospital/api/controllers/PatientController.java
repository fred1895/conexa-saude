package br.com.conexa.hospital.api.controllers;

import br.com.conexa.hospital.domain.adapters.IPatientService;
import br.com.conexa.hospital.domain.dto.PatientDto;
import br.com.conexa.hospital.infra.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PatientController {

    private final IPatientService service;

    public PatientController(IPatientService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid PatientDto patient) {
        service.saveFromDto(patient);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto findById(@PathVariable Long id) {
        return service.findDtoById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> findAll() {
        return service.getAllPatients();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody PatientDto dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
