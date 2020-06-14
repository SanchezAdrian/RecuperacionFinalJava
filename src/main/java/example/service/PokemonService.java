package example.service;

import java.util.List;

import example.entity.Pokemon;
import example.model.PokemonModel;

public interface PokemonService {
	

	public abstract List<PokemonModel> getListpokemon();
	public abstract Pokemon addPokemon(PokemonModel pokemonModel);
	public abstract int removePokemon(int id);
	public abstract Pokemon findOne(int id);
	Pokemon updatePokemon(PokemonModel pokemonModel, int id);
	

}
