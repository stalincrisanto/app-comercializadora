package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XEPER_PERFIL")
@Data
public class ProfileUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_perfil")
	private Long code;
	
	@Column(  name = "fec_asignacion_perfil",  nullable = false)
	private LocalDateTime assignmentDate;
	
	@Column(  name = "fec_expiracion_perfil")
	private LocalDateTime expirationDate;
	
	@Column( name = "fec_modificacion_perfil")
	private LocalDateTime modifiedDate;
	
	@Column( length = 300, name = "observation_perfil")
	private String observation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cod_user_perfil",nullable = false)
	@JsonIgnore
	private UserApp userProfile;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cod_sistema_perfil",nullable = false)
	@JsonIgnore
	private System systemProfile;
}
