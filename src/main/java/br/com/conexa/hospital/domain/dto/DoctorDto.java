package br.com.conexa.hospital.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DoctorDto implements Serializable {

    @JsonProperty("medico")
    private String name;

    @JsonProperty("data_hora_atendimento")
    private String specialty;
}
