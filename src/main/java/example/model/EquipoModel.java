package example.model;

import java.util.List;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

import example.entity.Entrenador;
import example.entity.Pokemon;

public class EquipoModel {
	
	@NonNull
	private Integer id;
	
	@NonNull
	private String nombre;
	
	
	private String descripcion;
	
	@Column(name="created_at")
	private String createdat;
	
	private EntrenadorModel entrenador;
	
	
	private List <Pokemon> PokemonsEquipo;


	public EquipoModel(Integer id, String nombre, String descripcion, String createdat, List<Pokemon> pokemonsEquipo, EntrenadorModel entrenador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.createdat = createdat;
		PokemonsEquipo = pokemonsEquipo;
		this.entrenador = entrenador;
	}
	
	public EquipoModel() {
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public List<Pokemon> getPokemonsEquipo() {
		return PokemonsEquipo;
	}

	public void setPokemonsEquipo(List<Pokemon> pokemonsEquipo) {
		PokemonsEquipo = pokemonsEquipo;
	}

	public EntrenadorModel getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(EntrenadorModel entrenador) {
		this.entrenador = entrenador;
	}
	
	
	

}
