package example.model;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.lang.NonNull;

import example.entity.Equipo;

public class EntrenadorModel {
	
	@NonNull
	private Integer id;
	
	@NonNull
	private String nombre;
	
	
	private String foto;
	
	
	private String telefono;
	
	@Email
	private String email;
	
	
	private List<Equipo> EquipoEntrenador;


	public EntrenadorModel(Integer id, String nombre, String foto, String telefono, String email,
			List<Equipo> equipoEntrenador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.foto = foto;
		this.telefono = telefono;
		this.email = email;
		EquipoEntrenador = equipoEntrenador;
	}
	
	public EntrenadorModel()  {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Equipo> getEquipoEntrenador() {
		return EquipoEntrenador;
	}

	public void setEquipoEntrenador(List<Equipo> equipoEntrenador) {
		EquipoEntrenador = equipoEntrenador;
	}
	
	
	

}
