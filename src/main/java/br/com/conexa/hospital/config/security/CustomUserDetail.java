package br.com.conexa.hospital.config.security;

import br.com.conexa.hospital.domain.entities.Doctor;
import br.com.conexa.hospital.domain.entities.Schedule;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Doctor doctor;

	public CustomUserDetail() {
	}
	
	public CustomUserDetail(Doctor doctor) {
		this.doctor = doctor;
		this.id = doctor.getId();
		this.email = doctor.getEmail();
		this.senha = doctor.getPassword();
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Doctor getDoctor() {
		return doctor;
	}

}
