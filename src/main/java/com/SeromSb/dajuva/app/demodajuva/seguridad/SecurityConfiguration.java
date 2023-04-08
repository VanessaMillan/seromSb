package com.SeromSb.dajuva.app.demodajuva.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.SeromSb.dajuva.app.demodajuva.service.UsuarioFacade;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	
	@Autowired
	private CustomAuthenticationHandler customAuthenticationHandler;
	
	private final UsuarioFacade usuarioFacade;
	
	public SecurityConfiguration(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioFacade);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests(auth -> auth
				.mvcMatchers("/app/demodajuva/administrador/**").hasAuthority("Administrador")
				.mvcMatchers("/app/demodajuva/**").hasAnyAuthority("Vendedor","Administrador")
				.mvcMatchers("/**").permitAll()		
				.anyRequest().authenticated())
		.userDetailsService(usuarioFacade)
		.formLogin()				
		.loginPage("/login").permitAll()
		.successHandler(customAuthenticationHandler)
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.permitAll();
		
		http.authenticationProvider(authenticationProvider());
		http.headers().frameOptions().sameOrigin();

		return http.build();
	}
}
