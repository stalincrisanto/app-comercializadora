package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IStateUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserAppDAO userRepository;

	// private IStateUserDAO stateRepository;

	@Override
	public List<UserApp> listUsers() {
		return (List<UserApp>) userRepository.findAll();
	}

	@Override
	public void newUser(UserApp user) {
		userRepository.save(user);
	}

	public UserApp findByCedula(String cedula) {
		return userRepository.findByDocument(cedula);
	}

	@Override
	public UserApp findUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String generatePassword() {
		int leftLimit = 48; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	@Bean
	public PasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

}
