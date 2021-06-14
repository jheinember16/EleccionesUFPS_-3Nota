package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Voto;

public interface IVotoDao extends CrudRepository<Voto,Integer>{

}
