package br.com.conexa.hospital.api.controllers;

import br.com.conexa.hospital.domain.adapters.IScheduleService;
import br.com.conexa.hospital.domain.dto.ScheduleDto;
import br.com.conexa.hospital.infra.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/medico")
public class DoctorController {

    private final BCryptPasswordEncoder encoder;

    private final IScheduleService scheduleService;

    public DoctorController(BCryptPasswordEncoder encoder, IScheduleService scheduleService) {
        this.encoder = encoder;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/agendamento")
    @ResponseStatus(HttpStatus.CREATED)
    public void scheduling(@RequestBody ScheduleDto dto) {
        scheduleService.saveFromDto(dto);
    }

    @GetMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
