package example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="pokemonMovimiento")
public class PokemonMovimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_movpoke")
	private Integer id_movpoke;
	
	@ManyToOne
	@JoinColumn(name="id_poke")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Pokemon pokemon;
	
	@ManyToOne
	@JoinColumn(name="id_mov1")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Movimiento mov1;
	
	@ManyToOne
	@JoinColumn(name="id_mov2")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Movimiento mov2;
	
	@ManyToOne
	@JoinColumn(name="id_mov3")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Movimiento mov3;
	
	@ManyToOne
	@JoinColumn(name="id_mov4")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Movimiento mov4;

	
	// se que esto es muy probable que se pudiese hacer con un array y ser mas optimo, pero las circunstancias de tiempo que me queda al hacerlo 
	// y la falta de creatividad del momento me hacen ir por la via rapida y facil, perdon :(
	
	public PokemonMovimiento() {
		
	}
	
	
	public PokemonMovimiento(Integer id_movpoke, Pokemon pokemon, Movimiento mov1, Movimiento mov2, Movimiento mov3,
			Movimiento mov4) {
		super();
		this.id_movpoke = id_movpoke;
		this.pokemon = pokemon;
		this.mov1 = mov1;
		this.mov2 = mov2;
		this.mov3 = mov3;
		this.mov4 = mov4;
	}

	public Integer getId_movpoke() {
		return id_movpoke;
	}

	public void setId_movpoke(Integer id_movpoke) {
		this.id_movpoke = id_movpoke;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Movimiento getMov1() {
		return mov1;
	}

	public void setMov1(Movimiento mov1) {
		this.mov1 = mov1;
	}

	public Movimiento getMov2() {
		return mov2;
	}

	public void setMov2(Movimiento mov2) {
		this.mov2 = mov2;
	}

	public Movimiento getMov3() {
		return mov3;
	}

	public void setMov3(Movimiento mov3) {
		this.mov3 = mov3;
	}

	public Movimiento getMov4() {
		return mov4;
	}

	public void setMov4(Movimiento mov4) {
		this.mov4 = mov4;
	}


	@Override
	public String toString() {
		return "PokemonMovimiento ENTIDAD [id_movpoke=" + id_movpoke + ", pokemon=" + pokemon.getNombre() + ", mov1=" + mov1.getNombre() + ", mov2="
				+ mov2.getNombre() + ", mov3=" + mov3.getNombre() + ", mov4=" + mov4.getNombre() + "]";
	}

	
	
	

}
