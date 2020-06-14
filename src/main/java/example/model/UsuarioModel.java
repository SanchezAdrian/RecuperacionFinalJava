package example.model;

import com.sun.istack.Nullable;



public class UsuarioModel {
private String username;
private String password;

@Nullable
private EntrenadorModel entrenador;

public UsuarioModel() {
	
}

public UsuarioModel(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public EntrenadorModel getEntrenador() {
	return entrenador;
}

public void setEntrenador(EntrenadorModel entrenador) {
	this.entrenador = entrenador;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
