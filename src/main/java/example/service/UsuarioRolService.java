package example.service;

import java.util.List;

import example.entity.UsuarioRol;
import example.model.UsuarioRolModel;

public interface UsuarioRolService {

	public abstract List<UsuarioRol> getListUsuarios();
	public abstract UsuarioRol addUsuario(UsuarioRol usuarioRol);
	UsuarioRol addUsuario(UsuarioRolModel usuarioRolModel);
}
