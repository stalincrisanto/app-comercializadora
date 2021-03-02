package ec.edu.espe.monster.GR10COMERCIALIZADORA.exception;

public enum AuthenticationExceptionCodes {
	STATE_USER_NOT_FOUND("EA01"),
	STATE_USER_UPDATE_CREDENTIAL("EA02"),
	STATE_USER_NO_AUTORIZED("EA03"),
	STATE_USER_EXPIRED_CREDENTIAL("EA04"),
	STATE_USER_NOT_REGISTER("EA05"),
	STATE_USER_UPDATE_TEMP_CREDENTIAL("EA06"),
	USER_BAD_CREDENTIAL("EA07");
	
	private String code;


	private AuthenticationExceptionCodes(String code) {
		this.code = code;
	}



	public String getCode() {
		return code;
	}

}
