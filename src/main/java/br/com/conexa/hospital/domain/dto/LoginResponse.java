package br.com.conexa.hospital.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class LoginResponse implements Serializable {
    private String token;
    private String medico;
    private String especialidade;

    @Transient
    @JsonProperty("data_hora_atendimento")
    private List<ScheduleDto> schedules = new ArrayList<>();
}
