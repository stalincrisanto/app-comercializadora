package ec.edu.espe.monster.GR10COMERCIALIZADORA.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationExceptionCodes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationCustomFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationCustomFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String username = obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO verificar
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		log.error(failed.getMessage());
		switch (failed.getMessage()) {
		case "EA01":
			throw new AuthenticationCustomException(
					"Nombre de Usuario o contraseña invalido, vuelve a intentarlo.", "/login?error",
					AuthenticationExceptionCodes.STATE_USER_NOT_FOUND);
		case "EA02":
			throw new AuthenticationCustomException("Al ser la primera vez que ingresa debe cambiar su contraseña",
			"/onboarding/change-credentials", AuthenticationExceptionCodes.STATE_USER_UPDATE_CREDENTIAL);
		case "EA03":
			throw new AuthenticationCustomException("", "/error/401",
					AuthenticationExceptionCodes.STATE_USER_NO_AUTORIZED);
		case "EA04":
			throw new AuthenticationCustomException("Las credenciales temporales expiraron.",
					"/login?error", AuthenticationExceptionCodes.STATE_USER_EXPIRED_CREDENTIAL);
		case "EA05":
			log.error(
					"[STATE-USER ERROR] user-message: Usted no tiene definido un estado, contáctese con el administrador del sistema");
			throw new AuthenticationCustomException(
					"Usted no tiene definido un estado, contáctese con el administrador del sistema", "/login?error",
					AuthenticationExceptionCodes.STATE_USER_NOT_FOUND);
		case "EA06":
			throw new AuthenticationCustomException("Cambie sus credenciales temporales",
					"/auth/change-credentials", AuthenticationExceptionCodes.STATE_USER_UPDATE_TEMP_CREDENTIAL);
		case "Bad credentials":
			throw new AuthenticationCustomException(
					"Nombre de Usuario o contraseña invalido, vuelve a intentarlo.", "/login?error",
					AuthenticationExceptionCodes.STATE_USER_NOT_FOUND);
		default:
			super.unsuccessfulAuthentication(request, response, failed);
			break;
		}
		
	}

}
