package web.entities;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@NamedQuery(name="Computador.findAll", query="SELECT c FROM Computador c")
public class Computador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	@Lob
	private String imagen;

	private double precio;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="marca")
	private Marca marcaBean;

	//bi-directional many-to-one association to Tipocomp
	@ManyToOne
	@JoinColumn(name="tipo")
	private Tipocomp tipocomp;

	//bi-directional one-to-one association to Inventario
	@OneToOne(mappedBy="computador")
	private Inventario inventario;

	public Computador() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Marca getMarcaBean() {
		return this.marcaBean;
	}

	public void setMarcaBean(Marca marcaBean) {
		this.marcaBean = marcaBean;
	}

	public Tipocomp getTipocomp() {
		return this.tipocomp;
	}

	public void setTipocomp(Tipocomp tipocomp) {
		this.tipocomp = tipocomp;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

}
