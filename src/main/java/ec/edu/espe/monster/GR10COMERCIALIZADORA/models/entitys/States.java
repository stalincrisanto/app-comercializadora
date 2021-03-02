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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


/**
 * Entidad que representa todos los posibles estados del usuario
 */
@Entity
@Table(name = "XEEST_ESTADO")
@Data
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_estado")
	private Long code;
	
	
	@Column(nullable = false, length = 50, name = "keyword_estado", unique = true)
	private String keyword;
	
	
	@Column( length = 100, name = "descripcion_estado")
	private String description;
	
	
	@Column( name = "vigente_estado")
	private Boolean current;
	
	@Column( name = "acceso_habilitado_estado")
	private Boolean enableAccess;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<StateUser> statesUser;
}
