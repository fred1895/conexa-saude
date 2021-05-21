package br.com.conexa.hospital.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "agendamento")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonIgnore
    private Doctor doctor;

    private LocalDateTime time;

}
