package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Tipo;
import example.model.TipoModel;
import example.repository.TipoJpaRepository;

@Component("tipoConverter")
public class TipoConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("tipoJpaRepository")
	private TipoJpaRepository tipoJpaRepository;
	
	//Entity --> Model
		public TipoModel entity2model(Tipo tipo) {
		Tipo saveTipo = tipoJpaRepository.save(tipo);
		TipoModel returnValue = modelMapper.map(saveTipo, TipoModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public Tipo model2entity(TipoModel tipoModel) {
			Tipo tipo = modelMapper.map(tipoModel, Tipo.class);
			return tipo;
		}
}
