package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

@Repository
public interface IUserAppDAO extends JpaRepository<UserApp, Long>{
	
	public Optional<UserApp> findByNickname(String nickname);
	
	@Query(value = "SELECT * FROM xeusa_usuario WHERE num_documento_usuario = ?1", nativeQuery = true)
	public UserApp findByDocument(String emailAddress);
}
