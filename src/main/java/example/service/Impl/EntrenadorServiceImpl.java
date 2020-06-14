package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.EntrenadorConverter;
import example.entity.Entrenador;
import example.model.EntrenadorModel;
import example.repository.EntrenadorJpaRepository;
import example.service.EntrenadorService;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {
	
	@Autowired
	@Qualifier("entrenadorJpaRepository")
	private EntrenadorJpaRepository entrenadorJpaRepository;
	
	@Autowired
	@Qualifier("entrenadorConverter")
	private EntrenadorConverter entrenadorConverter;
	
	@Override
	public List<EntrenadorModel> getListentrenador(){
		List<EntrenadorModel> entrenadorModel=new ArrayList<EntrenadorModel>();
		for(Entrenador entrenadorEntity: entrenadorJpaRepository.findAll())
			entrenadorModel.add(entrenadorConverter.entity2model(entrenadorEntity));
		return entrenadorModel;
	}
	
	@Override
	public Entrenador addEntrenador(EntrenadorModel entrenadorModel) {
		Entrenador entrenador=entrenadorConverter.model2entity(entrenadorModel);
		return entrenadorJpaRepository.save(entrenador);
	}
	
	@Override
	public Entrenador findOne(int id) {
		Entrenador entrenador = entrenadorJpaRepository.findById(id).get();
		return entrenador;
	}
	
	@Override
	public int removeEntrenador(int id) {
		entrenadorJpaRepository.deleteById(id);
		return 0;
	}
	
	@Override
	public Entrenador updateEntrenador(EntrenadorModel entrenadorModel, int id) {
		Entrenador entrenador=entrenadorConverter.model2entity(entrenadorModel);
		entrenador.setId(id);
		return entrenadorJpaRepository.save(entrenador);
	}
}
