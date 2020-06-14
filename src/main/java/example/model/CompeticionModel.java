package example.model;

import java.util.List;


import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import example.entity.Equipo;

public class CompeticionModel {
	

	
	@NonNull
	private int id;
	
	@NonNull
	@Size(min=2,max=8)
	private String nombre;
	
	private String fecha;
	
	private String lugar;
	
	private EquipoModel nomeq;
	
	private List<Equipo> EquiposCompiten;

	public CompeticionModel(int id, @Size(min = 2, max = 8) String nombre, String fecha, String lugar,
			EquipoModel nomeq, List<Equipo> equiposCompiten) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.nomeq = nomeq;
		EquiposCompiten = equiposCompiten;
	}
	
	
	public CompeticionModel() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public EquipoModel getNomeq() {
		return nomeq;
	}


	public void setNomeq(EquipoModel nomeq) {
		this.nomeq = nomeq;
	}


	public List<Equipo> getEquiposCompiten() {
		return EquiposCompiten;
	}


	public void setEquiposCompiten(List<Equipo> equiposCompiten) {
		EquiposCompiten = equiposCompiten;
	}

	
}
