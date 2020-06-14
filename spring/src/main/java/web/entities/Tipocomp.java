package web.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
@NamedQuery(name="Tipocomp.findAll", query="SELECT t FROM Tipocomp t")
public class Tipocomp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="tipocomp")
	private List<Computador> computadors;

	public Tipocomp() {
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

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setTipocomp(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setTipocomp(null);

		return computador;
	}

}

