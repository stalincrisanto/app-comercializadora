package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IStateUserDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.StateUser;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.UserApp;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IStateUserService;

@Service
public class StateUserImpl implements IStateUserService {

	@Autowired
	private IStateUserDAO stateRepository;
	
	@Override
	public void insertStateUserServ(StateUser state, UserApp user) {
		LocalDateTime asignacion = state.getAssignmentDate();
		LocalDateTime expiracion = state.getExpirationDatta();
		String observacion = state.getObservation();
		Long codUsuario = user.getCode();
		Long codEstado = (long) 1;
		
		stateRepository.insertStateUser(asignacion, expiracion, observacion, codEstado,codUsuario);
	}
	
}
