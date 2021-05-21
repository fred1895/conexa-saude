package br.com.conexa.hospital.infra.repositories;

import br.com.conexa.hospital.api.exceptions.ObjectNotFoundException;
import br.com.conexa.hospital.domain.entities.Schedule;
import br.com.conexa.hospital.domain.repositories.IScheduleRepository;
import br.com.conexa.hospital.infra.jpa.ScheduleJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleRepository implements IScheduleRepository {

    @Autowired
    private ScheduleJpa jpa;

    @Override
    public Schedule findById(Long id) {
        return jpa.findById(id).orElseThrow(() -> new ObjectNotFoundException("Agendamento nao encontrado"));
    }

    @Override
    public void save(Schedule schedule) {
        jpa.save(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return jpa.findAll();
    }

    @Override
    public void delete(Schedule schedule) {
        jpa.delete(schedule);
    }
}
