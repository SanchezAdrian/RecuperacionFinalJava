package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import example.entity.Competicion;
import example.model.CompeticionModel;
import example.repository.CompeticionJpaRepository;



@Component("competicionConverter")
public class CompeticionConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso
	
	
	@Autowired
	@Qualifier("competicionJpaRepository")
	private CompeticionJpaRepository competicionJpaRepository;
	
	//Entity --> Model
	public CompeticionModel entity2model(Competicion competicion) {
	Competicion saveCompeticion = competicionJpaRepository.save(competicion);
	CompeticionModel returnValue = modelMapper.map(saveCompeticion, CompeticionModel.class);
	return returnValue;
	
	
	}
	
	//Model 2 Entity
	public Competicion model2entity(CompeticionModel competicionModel) {
		Competicion competicion = modelMapper.map(competicionModel, Competicion.class);
		return competicion;
	}

}
