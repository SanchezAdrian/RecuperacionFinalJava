package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.EquipoCompite;
import example.model.EquipoCompiteModel;
import example.repository.EquipoCompiteJpaRepository;

@Component("equipoCompiteConverter")
public class EquipoCompiteConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("equipoCompiteJpaRepository")
	private EquipoCompiteJpaRepository equipoCompiteJpaRepository;
	
	//Entity --> Model
		public EquipoCompiteModel entity2model(EquipoCompite equipoCompite) {
		EquipoCompite saveEquipoCompite = equipoCompiteJpaRepository.save(equipoCompite);
		EquipoCompiteModel returnValue = modelMapper.map(saveEquipoCompite, EquipoCompiteModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public EquipoCompite model2entity(EquipoCompiteModel equipoCompiteModel) {
			EquipoCompite equipoCompite = modelMapper.map(equipoCompiteModel, EquipoCompite.class);
			return equipoCompite;
		}
}
