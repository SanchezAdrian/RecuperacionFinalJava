package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.PokemonConverter;
import example.entity.Pokemon;
import example.model.PokemonModel;
import example.repository.PokemonJpaRepository;
import example.service.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	@Autowired
	@Qualifier("pokemonJpaRepository")
	private PokemonJpaRepository pokemonJpaRepository;
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	@Override public List<PokemonModel> getListpokemon() {
		List<PokemonModel> pokemonModel=new ArrayList<PokemonModel>();
		for(Pokemon pokemonEntity: pokemonJpaRepository.findAll())
			pokemonModel.add(pokemonConverter.entity2model(pokemonEntity));
		return pokemonModel;
	}
	
	@Override
	public Pokemon addPokemon(PokemonModel pokemonModel) {
		Pokemon pokemon = pokemonConverter.model2entity(pokemonModel);
		return pokemonJpaRepository.save(pokemon);
	}
	
	@Override
	public Pokemon findOne(int id) {
		Pokemon pokemon = pokemonJpaRepository.findById(id).get();
		return pokemon;
	}
	
	@Override
	public Pokemon updatePokemon(PokemonModel pokemonModel, int id) {
		Pokemon pokemon=pokemonConverter.model2entity(pokemonModel);
		pokemon.setId(id);
		return pokemonJpaRepository.save(pokemon);
	}

	@Override
	public int removePokemon(int id) {
		pokemonJpaRepository.deleteById(id);
		return 0;
	}

}
