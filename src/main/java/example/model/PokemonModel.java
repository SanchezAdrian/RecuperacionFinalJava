package example.model;

import java.util.List;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

import example.entity.Equipo;
import example.entity.Movimiento;
import example.entity.PokemonMovimiento;

public class PokemonModel {
	
	@NonNull
	private Integer id;
	
	@NonNull
	private String nombre;
	
	@NonNull
	private String ataque;
	
	@NonNull
	private String defensa;
	
	@Column(name="foto")
	private String foto;
	

	@Column(name="equipoPokemon")
	private Equipo equiposPokemon;


	private List <PokemonMovimiento> movimientosPokemon;
	
	@NonNull
	private TipoModel tipoPokemon;

	public PokemonModel(Integer id, String nombre, String ataque, String defensa, String foto,
			 Equipo equiposPokemon, List<PokemonMovimiento> movimientosPokemon, TipoModel tipoPokemon) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ataque = ataque;
		this.defensa = defensa;
		this.foto = foto;
		this.movimientosPokemon = movimientosPokemon;
		this.equiposPokemon = equiposPokemon;
		this.tipoPokemon = tipoPokemon;
	}

	public PokemonModel() {
		
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

	public String getAtaque() {
		return ataque;
	}

	public void setAtaque(String ataque) {
		this.ataque = ataque;
	}

	public String getDefensa() {
		return defensa;
	}

	public void setDefensa(String defensa) {
		this.defensa = defensa;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<PokemonMovimiento> getMovimientosPokemon() {
		return movimientosPokemon;
	}

	public void setMovimientosPokemon(List<PokemonMovimiento> movimientosPokemon) {
		this.movimientosPokemon = movimientosPokemon;
	}
	
	public Equipo getEquiposPokemon() {
		return equiposPokemon;
	}

	public void setEquiposPokemon(Equipo equiposPokemon) {
		this.equiposPokemon = equiposPokemon;
	}

	public TipoModel getTipoPokemon() {
		return tipoPokemon;
	}

	public void setTipoPokemon(TipoModel tipoPokemon) {
		this.tipoPokemon = tipoPokemon;
	}
	
	
	
	
}
