package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XESIS_SISTEM")
@Data
public class System {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sistema")
	private Long id;

	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "nombre_sistema")
	private String name;
	
	@Column(length = 300, name = "descripcion_sistema")
	private String description;
	
	@NotEmpty(message = "La palabra clave del Sistema es necesario")
	@Column(nullable = false, length = 50, name = "keyword_sistema")
	private String keyword;
	
	
	@Column(nullable = false, name = "vigencia_sistema")
	private Boolean current;
	
	@ManyToOne
	@JoinColumn(name = "id_sistema_original", referencedColumnName = "id_sistema")
	private System originalSystem;
	
	@OneToMany(mappedBy = "originalSystem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<System> subSystems;
	
	@OneToMany(mappedBy = "system", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ResourceSystem> resources;
	
	@OneToMany(mappedBy = "systemProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ProfileUser> profiles;
}
