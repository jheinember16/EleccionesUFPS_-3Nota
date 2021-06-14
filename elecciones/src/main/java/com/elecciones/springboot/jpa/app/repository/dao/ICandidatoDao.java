package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Candidato;

public interface ICandidatoDao extends CrudRepository<Candidato, Integer>{

}
