package example.model;


import example.entity.Usuario;

public class UsuarioRolModel {
	private Integer userRoleId;
	private UsuarioModel usuario;
	private String role;
	
	public UsuarioRolModel() {
		
	}

	public UsuarioRolModel(Integer userRoleId, UsuarioModel usuario, String role) {
		super();
		this.userRoleId = userRoleId;
		this.usuario = usuario;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
