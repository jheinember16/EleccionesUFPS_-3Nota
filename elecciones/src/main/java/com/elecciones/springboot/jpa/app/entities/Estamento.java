package com.elecciones.springboot.jpa.app.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estamento database table.
 * 
 */
@Entity
@NamedQuery(name="Estamento.findAll", query="SELECT e FROM Estamento e")
public class Estamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Eleccion
	@ManyToOne
	@JoinColumn(name="eleccion")
	private Eleccion eleccionBean;

	//bi-directional many-to-one association to Voto
	@OneToMany(mappedBy="estamentoBean")
	private List<Voto> votos;

	public Estamento() {
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

	public Eleccion getEleccionBean() {
		return this.eleccionBean;
	}

	public void setEleccionBean(Eleccion eleccionBean) {
		this.eleccionBean = eleccionBean;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public Voto addVoto(Voto voto) {
		getVotos().add(voto);
		voto.setEstamentoBean(this);

		return voto;
	}

	public Voto removeVoto(Voto voto) {
		getVotos().remove(voto);
		voto.setEstamentoBean(null);

		return voto;
	}

}