package com.jsegomez.springadmin.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/* ============= Propiedades de la clase ============= */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 2, message = "Nombre debe tener un mínimo de 2 dígitos")
	@NotNull(message = "Favor ingrese un nombre")
	private String nombre;
		
	@Size(min = 2, message = "Apellido debe tener un mínimo de 2 dígitos")
	@NotNull(message = "Favor ingrese un apellido")
	private String apellido;
	
	@Email(message = "Favor ingrese un correo electrónico valido")
	@NotNull(message = "Favor ingresar correo electrónico")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "Favor ingresar fecha de registro")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String imagen; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
	private Pais pais;

	/* ============= Métodos SETTER y GETTER============= */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
