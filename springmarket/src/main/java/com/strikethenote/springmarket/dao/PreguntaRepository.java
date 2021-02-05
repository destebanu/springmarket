package com.strikethenote.springmarket.dao;

import org.springframework.stereotype.Repository;
import com.strikethenote.springmarket.entidades.Pregunta;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface PreguntaRepository extends PagingAndSortingRepository<Pregunta, Long> {
	

}
