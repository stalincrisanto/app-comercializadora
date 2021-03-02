package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO.- diseñado para desaclopar los servicios de login
 * a datos especificos 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginGetRequest {
	private String error;
	private Model model;
	private Principal principal;
	private RedirectAttributes flash;
}
