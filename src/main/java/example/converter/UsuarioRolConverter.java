package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.UsuarioRol;
import example.model.UsuarioRolModel;
import example.repository.UsuarioRolJpaRepository;


@Component("usuarioRolConverter")
public class UsuarioRolConverter {
	
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("usuarioRolJpaRepository")
	private UsuarioRolJpaRepository usuarioRolJpa;
	
	//Entity --> Model
		public UsuarioRolModel entity2model(UsuarioRol usuarioRol) {
		UsuarioRol saveUsuarioRol = usuarioRolJpa.save(usuarioRol);
		UsuarioRolModel returnValue = modelMapper.map(saveUsuarioRol, UsuarioRolModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public UsuarioRol model2entity(UsuarioRolModel usuarioRolModel) {
			UsuarioRol usuarioRol = modelMapper.map(usuarioRolModel, UsuarioRol.class);
			return usuarioRol;
		}
}



