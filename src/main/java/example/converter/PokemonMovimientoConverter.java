package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Pokemon;
import example.entity.PokemonMovimiento;
import example.model.PokemonModel;
import example.model.PokemonMovimientoModel;
import example.repository.PokemonMovimientoJpaRepository;

@Component("pokemonMovimientoConverter")
public class PokemonMovimientoConverter {
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("pokemonMovimientoJpaRepository")
	private PokemonMovimientoJpaRepository pokemonMovimientoJpaRepository;
	
	//Entity --> Model
		public PokemonMovimientoModel entity2model(PokemonMovimiento pokemonMovimiento) {
		PokemonMovimiento savepokemonMovimiento = pokemonMovimientoJpaRepository.save(pokemonMovimiento);
		Pokemon poke = pokemonMovimiento.getPokemon();
		PokemonModel pokeModel = pokemonConverter.entity2model(poke);
		PokemonMovimientoModel returnValue = modelMapper.map(savepokemonMovimiento, PokemonMovimientoModel.class);
		returnValue.setPoke(pokeModel);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public PokemonMovimiento model2entity(PokemonMovimientoModel pokemonMovimientoModel) {
			PokemonModel pokeModel = pokemonMovimientoModel.getPoke();
			Pokemon poke = pokemonConverter.model2entity(pokeModel);
			PokemonMovimiento pk = modelMapper.map(pokemonMovimientoModel, PokemonMovimiento.class);
			pk.setPokemon(poke);
			return pk;
		}
}
