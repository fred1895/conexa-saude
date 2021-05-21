package br.com.conexa.hospital.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "paciente")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "idade")
    private String age;

    @Column(name = "telefone")
    private String phone;

    @OneToMany(mappedBy = "patient")
    private List<Schedule> schedules = new ArrayList<>();
}
