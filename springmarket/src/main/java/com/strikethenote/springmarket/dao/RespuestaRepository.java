package com.strikethenote.springmarket.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.strikethenote.springmarket.entidades.Respuesta;

@Repository
public interface RespuestaRepository extends PagingAndSortingRepository <Respuesta, Long> {

}
