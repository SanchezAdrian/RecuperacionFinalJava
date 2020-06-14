package example.model;

import java.util.List;

import org.springframework.lang.NonNull;

import example.entity.Tipo;

public class MovimientoModel {

	@NonNull
	private Integer id;
	
	@NonNull
	private String nombre;
	
	@NonNull
	private String descripcion;
	
	private String precission;
	
	private String categoria;
	
	private String potencia;
	
	@NonNull
	private TipoModel tipoMovimiento;

	public MovimientoModel(Integer id, String nombre, String descripcion, String precission, String categoria,
			String potencia, TipoModel tipoMovimientoModel) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precission = precission;
		this.categoria = categoria;
		this.potencia = potencia;
		tipoMovimiento = tipoMovimientoModel;
	}
	
	public MovimientoModel() {
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecission() {
		return precission;
	}

	public void setPrecission(String precission) {
		this.precission = precission;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public TipoModel getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoModel Tipo) {
		tipoMovimiento = Tipo;
	}
	
	
}
