package com.elecciones.springboot.jpa.app.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * The persistent class for the voto database table.
 * 
 */
@Entity
@NamedQuery(name="Voto.findAll", query="SELECT v FROM Voto v")
public class Voto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String enlace;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechacreacion;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechavoto;

	private String uuid;

	//bi-directional many-to-one association to Candidato
	@ManyToOne
	@JoinColumn(name="candidato")
	private Candidato candidatoBean;

	//bi-directional many-to-one association to Estamento
	@ManyToOne
	@JoinColumn(name="estamento")
	private Estamento estamentoBean;

	//bi-directional many-to-one association to Votante
	@ManyToOne
	@JoinColumn(name="votante")
	private Votante votanteBean;

	public Voto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechavoto() {
		return this.fechavoto;
	}

	public void setFechavoto(Date fechavoto) {
		this.fechavoto = fechavoto;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Candidato getCandidatoBean() {
		return this.candidatoBean;
	}

	public void setCandidatoBean(Candidato candidatoBean) {
		this.candidatoBean = candidatoBean;
	}

	public Estamento getEstamentoBean() {
		return this.estamentoBean;
	}

	public void setEstamentoBean(Estamento estamentoBean) {
		this.estamentoBean = estamentoBean;
	}

	public Votante getVotanteBean() {
		return this.votanteBean;
	}

	public void setVotanteBean(Votante votanteBean) {
		this.votanteBean = votanteBean;
	}

}