package com.strikethenote.springmarket.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Imagen;

/* DAO = Repository */
@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long>{

}
