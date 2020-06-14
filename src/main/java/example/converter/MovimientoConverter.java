package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Movimiento;
import example.model.MovimientoModel;
import example.repository.MovimientoJpaRepository;

@Component("movimientoConverter")
public class MovimientoConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("movimientoJpaRepository")
	private MovimientoJpaRepository movimientoJpaRepository;
	
	//Entity --> Model
		public MovimientoModel entity2model(Movimiento movimiento) {
		Movimiento saveMovimiento = movimientoJpaRepository.save(movimiento);
		MovimientoModel returnValue = modelMapper.map(saveMovimiento, MovimientoModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public Movimiento model2entity(MovimientoModel movimientoModel) {
			Movimiento movimiento = modelMapper.map(movimientoModel, Movimiento.class);
			return movimiento;
		}
}
 