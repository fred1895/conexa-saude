package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.domain.dto.ScheduleDto;
import br.com.conexa.hospital.domain.entities.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LocalDateUtil {

    public static List<ScheduleDto> filterScheduleToday(List<Schedule> schedules) {
        List<ScheduleDto> dtos = schedules.stream().map(ScheduleDto::new).collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();
        int dayOfMonth = now.getDayOfMonth();

        List<ScheduleDto> filtered = dtos.stream().filter((dto -> dto.getTime().getDayOfMonth() == dayOfMonth)).collect(Collectors.toList());
        return filtered;
    }
}
