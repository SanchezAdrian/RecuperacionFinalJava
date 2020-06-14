package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Pokemon;
import example.model.PokemonModel;
import example.repository.PokemonJpaRepository;

@Component("pokemonConverter")
public class PokemonConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("pokemonJpaRepository")
	private PokemonJpaRepository pokemonJpaRepository;
	
	//Entity --> Model
		public PokemonModel entity2model(Pokemon pokemon) {
		Pokemon savePokemon = pokemonJpaRepository.save(pokemon);
		PokemonModel returnValue = modelMapper.map(savePokemon, PokemonModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public Pokemon model2entity(PokemonModel pokemonModel) {
			Pokemon pokemon = modelMapper.map(pokemonModel, Pokemon.class);
			return pokemon;
		}
}
