package example.service;

import java.util.List;

import example.entity.PokemonMovimiento;
import example.model.PokemonMovimientoModel;

public interface PokemonMovimientoService {
	
	public abstract List<PokemonMovimientoModel> getListPokemonMovimiento();
	public abstract PokemonMovimiento addPokemonMovimiento(PokemonMovimientoModel pokemonMovimientoModel);
	public abstract int removePokemonMovimiento(int id);
	
	public abstract PokemonMovimiento findOne(int id);
	PokemonMovimiento updatePokemonMovimiento(PokemonMovimientoModel pokemonMovimientoModel, int id);
	

}
