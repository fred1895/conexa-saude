package br.com.conexa.hospital.config.security;

import br.com.conexa.hospital.domain.adapters.IDoctorService;
import br.com.conexa.hospital.domain.entities.Doctor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IDoctorService service;

    public CustomUserDetailService(IDoctorService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = service.findByEmail(email);
        return new CustomUserDetail(doctor);
    }
}
