package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationCustomException;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.exception.AuthenticationExceptionCodes;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginGetRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginPostRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ILoginServices;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class LoginServicesImp implements ILoginServices {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUserAppDAO userDAO;

	@Override
	public String loginUser(LoginGetRequest request) {

		if (request != null) {
			Model model = request.getModel();
			RedirectAttributes flash = request.getFlash();

			if (request.getPrincipal() != null) {
				flash.addFlashAttribute("info", "El usuario ya inicio sesión.");
				return "redirect:/";
			}

			if (request.getError() != null) {
				model.addAttribute("error",
						"Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.");
			}
		} else {
			// TODO: lazar un 500
		}
		return "/onboarding/login";
	}

	@Override
	public String authUser(LoginPostRequest loginRequest, Model model) {
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		}catch (BadCredentialsException e) {
			log.error("[BAD-CREDENCIAL] user: {}", loginRequest.getUsername());
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		}catch (LockedException e) {
			log.error("[LOCKED ] user: {}", loginRequest.getUsername());
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		} catch (DisabledException e) {
			log.error("[DISABLE USER] user: {}", loginRequest.getUsername());
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		} catch (AccountExpiredException e) {
			log.error("[Expired-account]");
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		} catch (CredentialsExpiredException e) {
			log.error("[Expired-credencial]");
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		} catch (AuthenticationException e) {
			log.error("[AuthenticationException]   " + e.getMessage());
			throw new AuthenticationCustomException("Nombre de usuario o contraseña incorrecta, por favor vuelve a intentarlo.",
					"/onboarding/login", AuthenticationExceptionCodes.USER_BAD_CREDENTIAL);
		}
		
		UserApp user = userDAO.findByNickname(loginRequest.getUsername()).orElseThrow(() -> {
			log.error("[ERROR USER NOT FOUND]");
			return new UsernameNotFoundException("Credenciales invalidas");
		});
		
		this.validationStateOfUser(user.getCurrentState());
		

		return "/store/home";
	}
	
	private void validationStateOfUser(StateUser stateUser) {
		if (stateUser == null || stateUser.getState() == null) {
			throw new AuthenticationCustomException(
					"Usted no tiene definido un estado, contáctese con el administrador del sistema", "/onboarding/login",
					AuthenticationExceptionCodes.STATE_USER_NOT_FOUND);
		} else if (stateUser.getState() != null) {
			States state = stateUser.getState();

			switch (state.getKeyword()) {
			case "E001":
				throw new AuthenticationCustomException("Al ser la primera vez que ingresa debe cambiar su contraseña",
						"/onboarding/change-credentials", AuthenticationExceptionCodes.STATE_USER_UPDATE_CREDENTIAL);
			case "E002":
				break;
			case "E003":
				throw new AuthenticationCustomException("", "/error/401",
						AuthenticationExceptionCodes.STATE_USER_NO_AUTORIZED);
			case "E004":
			case "E005":
				if (stateUser.getExpirationDatta() != null) {
					if (stateUser.getExpirationDatta().isBefore(LocalDateTime.now())) {
						throw new AuthenticationCustomException("Las credenciales temporales expiraron.",
								"/onboarding/login", AuthenticationExceptionCodes.STATE_USER_EXPIRED_CREDENTIAL);
					} else {
						throw new AuthenticationCustomException("Cambie sus credenciales temporales",
								"/auth/change-credentials", AuthenticationExceptionCodes.STATE_USER_UPDATE_TEMP_CREDENTIAL);
					}
				} else {
					throw new AuthenticationCustomException("Las credenciales temporales expiraron.",
							"/onboarding/login", AuthenticationExceptionCodes.STATE_USER_EXPIRED_CREDENTIAL);
				}

			default:
				throw new AuthenticationCustomException(
						"Usted no tiene definido un estado, contáctese con el administrador del sistema", "/onboarding/login",
						AuthenticationExceptionCodes.STATE_USER_NOT_FOUND);
			}

		}

	}


}
