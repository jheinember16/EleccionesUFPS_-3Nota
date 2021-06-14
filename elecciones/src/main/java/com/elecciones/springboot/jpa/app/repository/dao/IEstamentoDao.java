package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Estamento;

public interface IEstamentoDao extends CrudRepository<Estamento,Integer>{

}
