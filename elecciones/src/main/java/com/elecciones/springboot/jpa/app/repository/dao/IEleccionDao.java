package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Eleccion;

public interface IEleccionDao extends CrudRepository<Eleccion,Integer>{

}
