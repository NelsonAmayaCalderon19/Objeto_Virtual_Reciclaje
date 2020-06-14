package web.entities;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@NamedQuery(name="Inventario.findAll", query="SELECT i FROM Inventario i")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_computador")
	private int idComputador;

	private int cantidad;

	//bi-directional one-to-one association to Computador
	@OneToOne
	@JoinColumn(name="id_computador")
	private Computador computador;

	public Inventario() {
	}

	public int getIdComputador() {
		return this.idComputador;
	}

	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Computador getComputador() {
		return this.computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

}
