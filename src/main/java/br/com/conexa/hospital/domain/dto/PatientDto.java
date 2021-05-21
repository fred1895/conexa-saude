package br.com.conexa.hospital.domain.dto;

import br.com.conexa.hospital.domain.entities.Patient;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Data
public class PatientDto implements Serializable {

    private Long id;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "cpf")
    @CPF(message = "{cpf}")
    private String cpf;

    @JsonProperty(value = "idade")
    private String age;

    @JsonProperty(value = "telefone")
    private String phone;

    public PatientDto() {
    }

    public PatientDto(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.cpf = patient.getCpf();
        this.age = patient.getAge();
        this.phone = patient.getPhone();
    }
}
