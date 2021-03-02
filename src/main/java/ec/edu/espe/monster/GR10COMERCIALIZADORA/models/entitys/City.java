package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ciudades")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_ciudad")
	private Long id;
	
	@NotEmpty(message = "El nombre del Sistema es necesario")
	@Column(nullable = false, length = 100, name = "nombre_ciudad")
	private String name;
	
	@Column(length = 300, name = "descripcion_ciudad")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia",nullable = false)
	@JsonIgnore
	private Province province;
}
