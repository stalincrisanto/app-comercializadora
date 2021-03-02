package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;


import org.springframework.security.core.AuthenticationException;

public class AuthenticationCustomException extends AuthenticationException{

	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String viewResolver;
	private AuthenticationExceptionCodes code;
	
	
	public AuthenticationCustomException(String message, String viewResolver, AuthenticationExceptionCodes code) {
		super("Error en la Autenticacion.");
		this.message = message;
		this.viewResolver = viewResolver;
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getViewResolver() {
		return viewResolver;
	}


	public void setViewResolver(String viewResolver) {
		this.viewResolver = viewResolver;
	}


	public AuthenticationExceptionCodes getCode() {
		return code;
	}


	public void setCode(AuthenticationExceptionCodes code) {
		this.code = code;
	}


}
