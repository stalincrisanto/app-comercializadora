package ec.edu.espe.monster.GR10COMERCIALIZADORA.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
	/**
	 * Retorna el Algoritmo de Encriptación para claves 
	 * actual según la versión de spring boot utilizada.
	 * @apiNote Con la version 2.4.3 Se utiliza BCrypt.
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		PasswordEncoder encoder = passwordEncoder();
		
		builder.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}


	/**
	 * Configuración de las peticiciones http:
	 * rutas públicas, rutas de acceso según los roles.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/img/**", "/store/**", "/onboarding/**").permitAll()
		.antMatchers("/pay/**").hasAnyRole("CLIENTE", "ADMIN")
		.antMatchers("/finance/**", "/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/inventories/**", "/billing/**").hasAnyRole("ADMIN", "INTERNO")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.addFilter(new AuthenticationCustomFilter(this.authenticationManager()))
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}
	
	

}
