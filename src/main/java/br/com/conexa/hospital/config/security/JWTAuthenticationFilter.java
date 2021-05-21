package br.com.conexa.hospital.config.security;

import br.com.conexa.hospital.domain.dto.CredenciaisDTO;
import br.com.conexa.hospital.domain.dto.LoginResponse;
import br.com.conexa.hospital.domain.dto.ScheduleDto;
import br.com.conexa.hospital.domain.entities.Schedule;
import br.com.conexa.hospital.infra.LocalDateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
    
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

		try {
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class);

	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsuario(), creds.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
	
		CustomUserDetail user = ((CustomUserDetail) auth.getPrincipal());
        String token = jwtUtil.generateToken(user.getUsername());
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");

        LoginResponse response = new LoginResponse();
        response.setMedico(user.getDoctor().getName());
        response.setEspecialidade(user.getDoctor().getSpecialty());

        List<Schedule> schedules = user.getDoctor().getSchedules();
        List<ScheduleDto> scheduleDtos = LocalDateUtil.filterScheduleToday(schedules);

        response.setSchedules(scheduleDtos);
        response.setToken(token);

        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(response);

        res.setContentType("application/json");
        res.setStatus(200);
        res.getWriter()
                .append(value);
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}
