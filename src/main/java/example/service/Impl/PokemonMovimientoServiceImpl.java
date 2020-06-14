package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.PokemonMovimientoConverter;
import example.entity.PokemonMovimiento;
import example.model.PokemonMovimientoModel;
import example.repository.PokemonMovimientoJpaRepository;
import example.service.PokemonMovimientoService;

@Service
public class PokemonMovimientoServiceImpl implements PokemonMovimientoService {
	
	@Autowired
	@Qualifier("pokemonMovimientoJpaRepository")
	private PokemonMovimientoJpaRepository pokemonMovimientoJpaRepository;
	
	@Autowired
	@Qualifier("pokemonMovimientoConverter")
	private PokemonMovimientoConverter pokemonMovimientoConverter;
	
	@Override public List<PokemonMovimientoModel> getListPokemonMovimiento() {
		List<PokemonMovimientoModel> pokemonMovimientoModel=new ArrayList<PokemonMovimientoModel>();
		for(PokemonMovimiento pokemonMovimientoEntity: pokemonMovimientoJpaRepository.findAll())
			pokemonMovimientoModel.add(pokemonMovimientoConverter.entity2model(pokemonMovimientoEntity));
		return pokemonMovimientoModel;
	}
	
	@Override
	public PokemonMovimiento addPokemonMovimiento(PokemonMovimientoModel pokemonMovimientoModel) {
		PokemonMovimiento pokemonMovimiento = pokemonMovimientoConverter.model2entity(pokemonMovimientoModel);
		return pokemonMovimientoJpaRepository.save(pokemonMovimiento);
	}
	
	@Override
	public PokemonMovimiento findOne(int id) {
		PokemonMovimiento pokemonMovimiento = pokemonMovimientoJpaRepository.findById(id).get();
		return pokemonMovimiento;
	}
	
	@Override
	public PokemonMovimiento updatePokemonMovimiento(PokemonMovimientoModel pokemonMovimientoModel, int id) {
		PokemonMovimiento pokemonMovimiento=pokemonMovimientoConverter.model2entity(pokemonMovimientoModel);
		pokemonMovimiento.setId_movpoke(id);
		return pokemonMovimientoJpaRepository.save(pokemonMovimiento);
	}

	@Override
	public int removePokemonMovimiento(int id) {
		pokemonMovimientoJpaRepository.deleteById(id);
		return 0;
	}

}
