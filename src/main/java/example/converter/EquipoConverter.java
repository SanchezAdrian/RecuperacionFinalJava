package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Equipo;
import example.model.EquipoModel;
import example.repository.EquipoJpaRepository;

@Component("equipoConverter")
public class EquipoConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("equipoJpaRepository")
	private EquipoJpaRepository equipoJpaRepository;
	
	//Entity --> Model
		public EquipoModel entity2model(Equipo equipo) {
		Equipo saveEquipo = equipoJpaRepository.save(equipo);
		EquipoModel returnValue = modelMapper.map(saveEquipo, EquipoModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public Equipo model2entity(EquipoModel equipoModel) {
			Equipo equipo = modelMapper.map(equipoModel, Equipo.class);
			return equipo;
		}
}
