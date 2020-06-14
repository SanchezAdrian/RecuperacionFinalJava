package example.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.UsuarioRolConverter;
import example.entity.UsuarioRol;
import example.model.UsuarioRolModel;
import example.repository.UsuarioRolJpaRepository;
import example.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {
	
	@Autowired
	@Qualifier("usuarioRolJpaRepository")
	private UsuarioRolJpaRepository usuarioRolJpa;
	
	@Autowired
	@Qualifier("usuarioRolConverter")
	private UsuarioRolConverter usuarioRolConverter;

	@Override
	public List<UsuarioRol> getListUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioRol addUsuario(UsuarioRolModel usuarioRolModel) {
		UsuarioRol user = usuarioRolConverter.model2entity(usuarioRolModel);
		return usuarioRolJpa.save(user);
	}

	@Override
	public UsuarioRol addUsuario(UsuarioRol usuarioRol) {
		// TODO Auto-generated method stub
		return null;
	}

}
