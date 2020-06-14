package example.model;

import org.springframework.lang.NonNull;

public class PokemonMovimientoModel {

	@Override
	public String toString() {
		return "PokemonMovimientoModel [id=" + id + ", pokemon=" + pokemon.getNombre() + ", mov1=" + mov1.getNombre() + ", mov2=" + mov2.getNombre()
				+ ", mov3=" + mov3.getNombre() + ", mov4=" + mov4.getNombre() + "]";
	}

	@NonNull
	private Integer id;
	
	public PokemonModel pokemon;
	
	private MovimientoModel mov1;
	
	private MovimientoModel mov2;
	
	private MovimientoModel mov3;
	
	private MovimientoModel mov4;

	public PokemonMovimientoModel() {}
	
	public PokemonMovimientoModel(Integer id, PokemonModel poke, MovimientoModel mov1, MovimientoModel mov2,
			MovimientoModel mov3, MovimientoModel mov4) {
		super();
		this.id = id;
		this.pokemon = poke;
		this.mov1 = mov1;
		this.mov2 = mov2;
		this.mov3 = mov3;
		this.mov4 = mov4;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PokemonModel getPoke() {
		return pokemon;
	}

	public void setPoke(PokemonModel poke) {
		this.pokemon = poke;
	}

	public MovimientoModel getMov1() {
		return mov1;
	}

	public void setMov1(MovimientoModel mov1) {
		this.mov1 = mov1;
	}

	public MovimientoModel getMov2() {
		return mov2;
	}

	public void setMov2(MovimientoModel mov2) {
		this.mov2 = mov2;
	}

	public MovimientoModel getMov3() {
		return mov3;
	}

	public void setMov3(MovimientoModel mov3) {
		this.mov3 = mov3;
	}

	public MovimientoModel getMov4() {
		return mov4;
	}

	public void setMov4(MovimientoModel mov4) {
		this.mov4 = mov4;
	}
	
	
	
	
}
