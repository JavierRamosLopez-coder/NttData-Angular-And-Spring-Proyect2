package com.nttdata.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.springboot.backend.apirest.models.entity.Cliente;

/**
 * Interfaz IClienteService
 * @author jramlope
 *
 */
public interface IClienteService {

	/**
	 * Método para listar todos los clientes en la BBDD
	 * @return
	 */
	public List<Cliente> findAll();
	
	/**
	 * Método de la clase Page para enumerar las páginas
	 * @return
	 */
	public Page<Cliente> findAll(Pageable pageable);
	
	/**
	 * Método para insertar un nuevo cliente
	 */
	public Cliente save(Cliente cliente);
	
	/**
	 * Método para borrar un cliente
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * Método para encontrar un cliente por su id
	 * @param id
	 * @return
	 */
	public Cliente findById(Long id);
	
}
