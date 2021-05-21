package br.com.conexa.hospital.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "medico")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "especialidade")
    private String specialty;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Schedule> schedules = new ArrayList<>();

}
