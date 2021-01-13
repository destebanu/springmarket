package com.strikethenote.springmarket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.strikethenote.springmarket.entidades.Rol;


@Repository
@Component("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
}