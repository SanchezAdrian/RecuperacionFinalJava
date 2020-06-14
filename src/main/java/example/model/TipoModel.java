package example.model;

import org.springframework.lang.NonNull;

public class TipoModel {


	@NonNull
	private Integer id;
	@NonNull
	private String nombre;
	public TipoModel(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public TipoModel() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}