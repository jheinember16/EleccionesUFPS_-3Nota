package com.elecciones.springboot.jpa.app.repository.dao;

import org.springframework.data.repository.CrudRepository;

import com.elecciones.springboot.jpa.app.entities.Tipodocumento;

public interface ITipodocumentoDao extends CrudRepository<Tipodocumento,Integer>{

}
