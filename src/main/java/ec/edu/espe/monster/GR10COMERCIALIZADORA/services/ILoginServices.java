package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;


import org.springframework.ui.Model;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginGetRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginPostRequest;

public interface ILoginServices {

	public String loginUser(LoginGetRequest request);
	
	public String authUser(LoginPostRequest loginRequest, Model model);
}
