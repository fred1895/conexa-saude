package br.com.conexa.hospital.infra.jpa;

import br.com.conexa.hospital.domain.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpa extends JpaRepository<Schedule, Long> {
}
