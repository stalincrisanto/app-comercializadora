package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XEDID_DIRDOM")
@Data
public class AddressHome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_domicilio")
	private Long code;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "provincia_domicilio")
	private String province;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "ciudad_domicilio")
	private String city;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column( length = 100, name = "barrio_domicilio")
	private String neighborhood;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "calle_prin_domicilio")
	private String mainStreet;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(length = 100,  name = "calle_sec_domicilio")
	private String secondaryStreet;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "num_domicilio")
	private String houseNumbering;
	
	@OneToOne(mappedBy = "addressHome")
	@JsonIgnore
    private  UserApp user;
}
