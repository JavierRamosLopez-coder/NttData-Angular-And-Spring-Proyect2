package com.nttdata.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.springboot.backend.apirest.models.entity.Cliente;

@Repository
/**
 * Interfaz IClienteDAO
 * @author jramlope
 *
 */
public interface IClienteDao extends JpaRepository<Cliente, Long>{

	
	
}
