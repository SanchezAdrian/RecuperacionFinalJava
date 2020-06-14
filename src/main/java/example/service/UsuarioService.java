package example.service;

import java.util.List;

import example.model.UsuarioModel;

public interface UsuarioService {

	public abstract List<UsuarioModel> getListUsuarios();
	public abstract UsuarioModel addUsuario(UsuarioModel usuarioModel);
}
