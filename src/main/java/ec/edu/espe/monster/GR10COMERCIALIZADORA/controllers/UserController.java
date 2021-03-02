package ec.edu.espe.monster.GR10COMERCIALIZADORA.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.AddressHome;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IAddressHomeService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.ILoginServices;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStateUserService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp.SendMailServiceImpl;
import jdk.internal.org.jline.utils.Log;



@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAddressHomeService addressService;
	
	@Autowired
	private IStateUserService stateUser;
	
	@Autowired
	private SendMailServiceImpl sendMailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/users/users-view")
	public String usersController(Model model){
		List<UserApp> listadoUsuarios = userService.listUsers();
		List<AddressHome> listadoDirecciones = addressService.listAddressHome();
		UserApp user = new UserApp();
		model.addAttribute("titulo","Lista de Usuarios");
		model.addAttribute("usuarios",listadoUsuarios);
		model.addAttribute("direcciones", listadoDirecciones);
		model.addAttribute("usuario",user);
		return "/users/users-view";
	}
	
	@PostMapping("/newUser")
	public String newUser(@ModelAttribute UserApp user, StateUser state)
	{
		String password = userService.generatePassword();
		user.setDateCreated(LocalDateTime.now());
		user.setDateOfBirth(LocalDateTime.now());
		//PASSWORD
		String encryptPass = passwordEncoder.encode(password);
		user.setPassword(encryptPass);
		userService.newUser(user);
		
		state.setAssignmentDate(LocalDateTime.now());
		state.setExpirationDatta(null);
		state.setObservation(null);
		state.setUser(user);
		stateUser.insertStateUserServ(state, user);
		
		String message = "\n\nSu password para ingresar por primera vez es: " +password; 
		
		sendMailService.sendMail("comercializadorachinitos@gmail.com",user.getEmail(),"Acceso por primera vez", message);
		
		return "redirect:/users/users-view";
	}
	
}
