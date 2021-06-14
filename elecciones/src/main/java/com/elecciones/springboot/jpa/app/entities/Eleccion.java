package com.elecciones.springboot.jpa.app.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the eleccion database table.
 * 
 */
@Entity
@NamedQuery(name="Eleccion.findAll", query="SELECT e FROM Eleccion e")
public class Eleccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String cargo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechafin;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechainicio;

	private String nombre;

	//bi-directional many-to-one association to Candidato
	@OneToMany(mappedBy="eleccionBean")
	private List<Candidato> candidatos;

	//bi-directional many-to-one association to Estamento
	@OneToMany(mappedBy="eleccionBean")
	private List<Estamento> estamentos;

	//bi-directional many-to-one association to Votante
	@OneToMany(mappedBy="eleccionBean")
	private List<Votante> votantes;

	public Eleccion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Candidato> getCandidatos() {
		return this.candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public Candidato addCandidato(Candidato candidato) {
		getCandidatos().add(candidato);
		candidato.setEleccionBean(this);

		return candidato;
	}

	public Candidato removeCandidato(Candidato candidato) {
		getCandidatos().remove(candidato);
		candidato.setEleccionBean(null);

		return candidato;
	}

	public List<Estamento> getEstamentos() {
		return this.estamentos;
	}

	public void setEstamentos(List<Estamento> estamentos) {
		this.estamentos = estamentos;
	}

	public Estamento addEstamento(Estamento estamento) {
		getEstamentos().add(estamento);
		estamento.setEleccionBean(this);

		return estamento;
	}

	public Estamento removeEstamento(Estamento estamento) {
		getEstamentos().remove(estamento);
		estamento.setEleccionBean(null);

		return estamento;
	}

	public List<Votante> getVotantes() {
		return this.votantes;
	}

	public void setVotantes(List<Votante> votantes) {
		this.votantes = votantes;
	}

	public Votante addVotante(Votante votante) {
		getVotantes().add(votante);
		votante.setEleccionBean(this);

		return votante;
	}

	public Votante removeVotante(Votante votante) {
		getVotantes().remove(votante);
		votante.setEleccionBean(null);

		return votante;
	}

}