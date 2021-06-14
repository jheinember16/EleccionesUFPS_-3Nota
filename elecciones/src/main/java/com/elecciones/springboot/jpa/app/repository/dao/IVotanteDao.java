package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Votante;

public interface IVotanteDao extends CrudRepository<Votante,Integer>{

}
