package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XEEST_ESTUSU")
@Data
public class StateUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_est_usu")
	private Long code;
	
	@Column( name = "fec_asignacion_estusu",  nullable = false)
	private LocalDateTime assignmentDate;
	
	@Column(  name = "fec_expiracion_estusu")
	private LocalDateTime expirationDatta;
	
	@Column( length = 300, name = "observation_estusu")
	private String observation;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cod_user", referencedColumnName = "codigo_usuario",nullable = false)
    private UserApp user;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cod_estado",nullable = false)
	@JsonIgnore
	private States state;
}
