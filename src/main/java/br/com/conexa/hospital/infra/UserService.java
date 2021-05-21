package br.com.conexa.hospital.infra;

import br.com.conexa.hospital.config.security.CustomUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
	
	public static CustomUserDetail authenticated() {
		try {
			return (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
