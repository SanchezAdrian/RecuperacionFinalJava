package example.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="pokemon")
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_poke")
	private Integer id_poke;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="ataque")
	private String ataque;
	
	@Column(name="defensa")
	private String defensa;
	
	@Column(name="foto")
	private String foto;
	
	@ManyToOne 
	@JoinColumn(name="id_equ")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Equipo equiposPokemon;

	
	@ManyToOne
	@JoinColumn(name="id_tipo")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Tipo tipoPokemon;
	
	@OneToMany(mappedBy="id_movpoke",cascade=CascadeType.REMOVE)
	private List <PokemonMovimiento> movimientos;

	
	
	public Pokemon(Integer id_poke, String nombre, String ataque, String defensa, String foto, Equipo equiposPokemon,
			List<Movimiento> movimientosPokemon, Tipo tipoPokemon, List<PokemonMovimiento> movimientos) {
		super();
		this.id_poke = id_poke;
		this.nombre = nombre;
		this.ataque = ataque;
		this.defensa = defensa;
		this.foto = foto;
		this.equiposPokemon = equiposPokemon;
		this.tipoPokemon = tipoPokemon;
		this.movimientos = movimientos;
	}

	public Pokemon() {
		
	}

	public Integer getId() {
		return id_poke;
	}

	public void setId(Integer id) {
		this.id_poke = id;
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

	
	public Equipo getEquiposPokemon() {
		return equiposPokemon;
	}

	public void setEquiposPokemon(Equipo equiposPokemon) {
		this.equiposPokemon = equiposPokemon;
	}

	public Integer getId_poke() {
		return id_poke;
	}

	public void setId_poke(Integer id_poke) {
		this.id_poke = id_poke;
	}

	public Tipo getTipoPokemon() {
		return tipoPokemon;
	}

	public void setTipoPokemon(Tipo tipoPokemon) {
		this.tipoPokemon = tipoPokemon;
	}

	public List<PokemonMovimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<PokemonMovimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
	

}
