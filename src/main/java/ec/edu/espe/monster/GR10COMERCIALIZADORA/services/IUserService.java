package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

public interface IUserService {
	public List<UserApp> listUsers();
	public void newUser (UserApp user);
	public UserApp findByCedula(String cedula);
	public UserApp findUserById(Long id);
	public void deleteUser (Long id);
	public PasswordEncoder encode(); 
	public String generatePassword();
}
