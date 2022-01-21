package com.nttdata.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
/**
 * Entidad Cliente
 * @author jramlope
 *
 */
public class Cliente implements Serializable {

	/** Atributo Serial **/
	private static final long serialVersionUID = 1L;

	/** Atributo ID de tipo Long **/
	private Long id;

	/** Atributo nombre de tipo String **/
	private String nombre;

	/** Atributo apellido de tipo String **/
	private String apellido;

	/** Atributo email de tipo String **/
	private String email;

	/** Atributo fecha de tipo Date **/
	private Date fecha;
	
	/** Atributo foto de tipo String **/
	private String foto;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	@NotEmpty
	@Size(min = 4, max = 12)
	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	@NotEmpty
	@Size(min = 2)
	@Column(name = "apellido")
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	@NotEmpty
	@Email
	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fecha
	 */
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
