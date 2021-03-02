package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.time.LocalDateTime;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;

public interface IStateUserService {
	public void insertStateUserServ (StateUser state, UserApp user);
}
