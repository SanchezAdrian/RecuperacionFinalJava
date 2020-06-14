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
@Table(name="equipo")
public class Equipo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="created_at")
	private String createdat;
	
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<Pokemon> pokemons;
	
	@ManyToOne
	@JoinColumn(name="id_ent")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Entrenador entrenador;
	
	@OneToMany(mappedBy="id_equ_comp",cascade=CascadeType.REMOVE)
	private List <EquipoCompite> CompeticionEquipo;


	

	public Equipo(int id, String nombre, String descripcion, String createdat, List<Pokemon> pokemonsEquipo, Entrenador entrenador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.createdat = createdat;
		this.pokemons = pokemonsEquipo;
		this.entrenador = entrenador;
	}

	public Equipo() {
		
	}
	
	
	
	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Pokemon> getPokemonsEquipo() {
		return pokemons;
	}

	public void setPokemonsEquipo(List<Pokemon> pokemonsEquipo) {
		this.pokemons = pokemonsEquipo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	
	
}
