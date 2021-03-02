package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

@Repository
public interface IStateUserDAO extends JpaRepository<StateUser, Long>{
	@Modifying
	@Transactional
	@Query(value = "insert into  xeest_estusu (fec_asignacion_estusu, fec_expiracion_estusu, observation_estusu, fk_cod_estado, fk_cod_user) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	void insertStateUser(LocalDateTime asignacion, LocalDateTime expiracion, String observacion, Long codUsuario, Long codEstado);
}
