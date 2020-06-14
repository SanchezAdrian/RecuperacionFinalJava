package example.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="entrenador")
public class Entrenador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_ent")
	private int id_ent;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Equipo> equipos;
	
	@OneToOne(mappedBy ="entrenador")
	private Usuario usuario;
	
	public Entrenador(int id_ent, String nombre, String foto, String telefono, String email,
			List <Equipo> equipoEntrenador) {
		super();
		this.id_ent = id_ent;
		this.nombre = nombre;
		this.foto = foto;
		this.telefono = telefono;
		this.email = email;
		this.equipos = equipoEntrenador;
	}

	public List <Equipo> getEquipoEntrenador() {
		return equipos;
	}

	public void setEquipoEntrenador(List <Equipo> equipoEntrenador) {
		equipos = equipoEntrenador;
	}
	
	public Entrenador() {
		
	}

	public int getId() {
		return id_ent;
	}

	public void setId(int id) {
		this.id_ent = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
