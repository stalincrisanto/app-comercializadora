package ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.ProfileUser;

public interface IProfileUserDAO extends JpaRepository<ProfileUser, Long>{

}
