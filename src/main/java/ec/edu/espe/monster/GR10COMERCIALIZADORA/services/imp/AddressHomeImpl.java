package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.DAOs.IAddressHomeDAO;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.AddressHome;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IAddressHomeService;

@Service
public class AddressHomeImpl implements IAddressHomeService {

	@Autowired
	private IAddressHomeDAO addressRepository;
	
	@Override
	public List<AddressHome> listAddressHome() {
		return (List<AddressHome>)addressRepository.findAll();
	}

}
