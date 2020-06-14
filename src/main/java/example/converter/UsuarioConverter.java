package example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import example.entity.Usuario;
import example.model.UsuarioModel;
import example.repository.UsuarioJpaRepository;

@Component("usuarioConverter")
public class UsuarioConverter {
	
	ModelMapper modelMapper = new ModelMapper(); 
	// Uso la libreria Model Mapper debido a que tras tratar de hacerlo de la forma explicada en las diapositivas
	// me encontre con el error de que no me permite cambiar los atributos que son de un tipo Modelo/entidad y para ello
	// buscando informacion encontre esta libreria y encontre oportuno su uso

	@Autowired
	@Qualifier("usuarioJpaRepository")
	private UsuarioJpaRepository usuarioJpaRepository;
	
	//Entity --> Model
		public UsuarioModel entity2model(Usuario usuario) {
		Usuario saveUsuario = usuarioJpaRepository.save(usuario);
		UsuarioModel returnValue = modelMapper.map(saveUsuario, UsuarioModel.class);
		return returnValue;
		
		
		}
		
		//Model 2 Entity
		public Usuario model2entity(UsuarioModel usuarioModel) {
			Usuario usuario = modelMapper.map(usuarioModel, Usuario.class);
			return usuario;
		}
}
