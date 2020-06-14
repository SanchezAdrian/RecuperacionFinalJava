package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Entrenador;
import example.model.EntrenadorModel;
import example.repository.EntrenadorJpaRepository;

@Component("entrenadorConverter")
public class EntrenadorConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso
	
	
	@Autowired
	@Qualifier("entrenadorJpaRepository")
	private EntrenadorJpaRepository entrenadorJpaRepository;
	
	//Entity2Model
	
	public EntrenadorModel entity2model(Entrenador entrenador) {
		Entrenador saveEntrenador = entrenadorJpaRepository.save(entrenador);
		EntrenadorModel returnValue = modelMapper.map(saveEntrenador, EntrenadorModel.class);
		return returnValue;
	}
	
	
	//Model2Entity
	
	public Entrenador model2entity(EntrenadorModel entrenadorModel) {
		Entrenador entrenador = modelMapper.map(entrenadorModel, Entrenador.class);
		return entrenador;
	}
}
