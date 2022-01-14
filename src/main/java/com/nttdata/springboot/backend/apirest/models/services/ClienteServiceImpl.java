package com.nttdata.springboot.backend.apirest.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nttdata.springboot.backend.apirest.models.dao.IClienteDao;
import com.nttdata.springboot.backend.apirest.models.entity.Cliente;

@Service
/**
 * Servicio ClienteServiceImpl
 * 
 * @author jramlope
 *
 */
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	/** Instanciación de la interfaz IClienteDao **/
	private IClienteDao clienteDao;

	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}


}
