package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pronvicias")
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_provincia")
	private Long id;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "nombre_provincia")
	private String name;
	
	@Column(length = 300, name = "descripcion_provincia")
	private String description;
	
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<City> citys;
}
