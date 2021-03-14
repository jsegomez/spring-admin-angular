package com.jsegomez.springadmin.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// =============================== Propiedades de la clase ===============================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Favor proporcione un nombre")
	private String nombre;
	
	@NotNull(message = "Favor proporcione un apellido")
	private String apellido;
	
	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;
		
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles", // De esta manera podemos especificar el nombre de la table intermedia 
			joinColumns = @JoinColumn(name ="usuario_id"), // De está manera indicamos el foreing key
			inverseJoinColumns = @JoinColumn(name ="rol_id"), // De esta manera indicamos el nombre en tabla relacionada
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})} // indicamos que un usuario no puede repetir un rol, es una restricción
		)
	private List<Rol> roles;
	
	// =============================== Métodos Getter y Setter ===============================
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	} 

}
