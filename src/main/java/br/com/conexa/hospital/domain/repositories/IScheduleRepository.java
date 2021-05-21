package br.com.conexa.hospital.domain.repositories;

import br.com.conexa.hospital.domain.entities.Schedule;

import java.util.List;

public interface IScheduleRepository {

    Schedule findById(Long id);

    void save(Schedule schedule);

    List<Schedule> findAll();

    void delete(Schedule schedule);
}
