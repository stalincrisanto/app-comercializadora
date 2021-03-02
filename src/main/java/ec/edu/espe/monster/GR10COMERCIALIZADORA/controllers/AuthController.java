package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginGetRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.LoginPostRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ILoginServices;

@Controller
public class AuthController {
	
	@Autowired
	private ILoginServices loginServices;
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes flash) {
		return loginServices.loginUser(new LoginGetRequest(error, model, principal, flash));
	}
	
	@PostMapping("/onboarding/login")
	public String validLogin(LoginPostRequest requestLogin, Model model) {
		return loginServices.authUser(requestLogin, model);
	}
	
	@GetMapping("/signup")
	public String signup(Model model, Principal principal) {
		return "/onboarding/register";
	}
	
	@GetMapping("/auth/change-credentials")
	public String changeCredential(Model model, Principal principal) {
		return "/onboarding/change-credentials";
	}
	
	@PostMapping("/auth/change-credentials")
	public String updateCredential(Model model, Principal principal) {
		return "redirect:/";
	}
}
