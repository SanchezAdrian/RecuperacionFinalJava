package example.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="movimiento")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_mov")
	private Integer id_mov;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precission")
	private String precission;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="potencia")
	private String potencia;
	
	@ManyToOne
	@JoinColumn(name="id_tipo")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Tipo tipoMovimiento;

	public Integer getId() {
		return id_mov;
	}

	public void setId(Integer id) {
		this.id_mov = id;
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

	public Tipo getMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Tipo mov) {
		tipoMovimiento = mov;
	}

	public Movimiento(Integer id, String nombre, String descripcion, String precission, String categoria,
			String potencia,  Tipo tipoMovimiento) {
		super();
		this.id_mov = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precission = precission;
		this.categoria = categoria;
		this.potencia = potencia;
		tipoMovimiento = tipoMovimiento;
	}
	
	public Movimiento() {
		
	}

}
