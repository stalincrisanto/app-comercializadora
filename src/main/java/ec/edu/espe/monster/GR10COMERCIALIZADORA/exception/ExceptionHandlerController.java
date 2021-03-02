package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Maneja los errores, captura las excepciones
 * mapea una excepcion.
 */
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(AuthenticationCustomException.class)
	public String authenticationCustomExceptionHandler(AuthenticationCustomException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return ex.getViewResolver();
	}
	
}
