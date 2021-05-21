package br.com.conexa.hospital.domain.dto;

import br.com.conexa.hospital.domain.entities.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ScheduleDto implements Serializable {

    @JsonProperty("data_hora_atendimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @JsonProperty("id_paciente")
    private Long patientId;

    public ScheduleDto() {
    }

    public ScheduleDto(Schedule schedule) {
        this.time = schedule.getTime();
        this.patientId = schedule.getPatient().getId();
    }

}
