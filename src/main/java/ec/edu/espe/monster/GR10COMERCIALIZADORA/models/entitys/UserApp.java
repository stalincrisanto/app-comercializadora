package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "XEUSA_USUARIO")
@Data
public class UserApp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_usuario")
	private Long code;
	
	@Column(nullable = false, length = 100, name = "username_usuario", unique = true)
	private String nickname;
	
	@Column(nullable = false, length = 90, name = "password_usuario")
	private String password;
	
	@Column( length = 150, name = "nombres_usuario", nullable = false)
	private String names;
	
	@Column( length = 150, name = "apellidos_usuario", nullable = false)
	private String lastnames;
	
	@Column( length = 100, name = "correo_usuario", nullable = false)
	private String email;
	
	@Column( length = 30, name = "telefono_usuario")
	private String phone;
	
	@Column( length = 50, name = "num_documento_usuario", nullable = false)
	private String numDocument;
	
	@Column( length = 300, name = "discapacidad_usuario")
	private String disability;
	
	@Column(  name = "fec_nacimiento_usuario",  nullable = true)
	private LocalDateTime dateOfBirth;
	
	@Column(  name = "fec_creacion_usuario",  nullable = true)
	private LocalDateTime dateCreated;
	
	@Column( name = "fec_modificacion_usuario")
	private LocalDateTime dateModified;
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
    private StateUser currentState;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cod_domicilio", referencedColumnName = "codigo_domicilio",nullable = false)
	@JsonIgnore
    private AddressHome addressHome;
	
	@OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ProfileUser> profiles;
	
	
}
