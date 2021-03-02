package ec.edu.espe.monster.GR10COMERCIALIZADORA.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IUserAppDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import lombok.extern.slf4j.Slf4j;

/**
 * Este servicio es utilizado para buscar un usuario por el username(nickname) y
 * autenticarlo
 */
@Service
@Primary
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private IUserAppDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserApp user = userDAO.findByNickname(username).orElseThrow(() -> {
			log.error("[ERROR USER NOT FOUND]");
			return new UsernameNotFoundException("Credenciales invalidas");
		});

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// TODO: cargar roles del usuario
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		return new User(user.getNickname(), user.getPassword(), true, true, true, true, authorities);
	}

	
}
