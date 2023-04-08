package com.SeromSb.dajuva.app.demodajuva.seguridad;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("Administrador"))) {
			setDefaultTargetUrl("/app/demodajuva/administrador/pagina-principal");
		} else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("Vendedor"))) {
			setDefaultTargetUrl("/app/demodajuva/vendedor");
		} else {
			setDefaultTargetUrl("/cliente");
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
