package br.com.conexa.hospital.domain.adapters;

import br.com.conexa.hospital.domain.dto.ScheduleDto;
import br.com.conexa.hospital.domain.entities.Schedule;

public interface IScheduleService {
    void saveFromDto(ScheduleDto dto);

    void delete(Schedule schedule);
}
