package com.nttdata.springboot.backend.apirest.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.springboot.backend.apirest.models.entity.Cliente;
import com.nttdata.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
/**
 * Controlador ClienteRestController
 * 
 * @author jramlope
 *
 */
public class ClienteRestController {

	@Autowired
	/** Instanciación del servicio IClienteService **/
	private IClienteService clienteService;

	@GetMapping("/clientes")
	/**
	 * Método que muestra todos los clientes registrados en la BBDD
	 * 
	 * @return
	 */
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	/**
	 * Método que muestra un cliente por su id
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<?> show(@PathVariable Long id) {

		// Instanciación de un objeto de la clase Cliente a null
		Cliente cliente = null;

		// Creación de una colección de tipo Map
		Map<String, Object> response = new HashMap<>();

		try {

			// Llamado al método que realiza la lógica de este método
			cliente = clienteService.findById(id);

		} catch (DataAccessException e) {

			// Controlamos con un try catch los posibles fallos.
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Establecemos condiciones por si el cliente no existe
		if (cliente == null) {
			response.put("mensaje", "El cliente con id : ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	/**
	 * Método para añadir un cliente
	 * 
	 * @param cliente
	 * @return
	 */
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		// Instanciación de un objeto de la clase Cliente a null
		Cliente newCliente = null;

		// Creación de una colección de tipo Map
		Map<String, Object> response = new HashMap<>();

		// Establecimiento de condiciones para ver si hay fallos
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "El campo " + e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());

			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			newCliente = clienteService.save(cliente);

		} catch (DataAccessException e) {

			// Controlamos con un try catch los posibles fallos.
			response.put("mensaje", "Error al crear un nuevo cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Mensaje de satisfacción, por si el usuario se añade correctamente
		response.put("mensaje", "El cliente ha sido ingresado con éxito");
		response.put("cliente", newCliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/clientes/{id}")
	/**
	 * Método para modificar un cliente
	 * 
	 * @param cliente
	 * @param id
	 * @return
	 */
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Long id) {

		// Obtención de los datos de los clientes a través del método findById
		// para su posterior almacenamiento en una variable de tipo Cliente
		Cliente clienteActual = clienteService.findById(id);

		// Instanciación a null de un objeto de la clase Cliente
		Cliente clienteActualizado = null;

		// Creación de un objeto de la clase Map
		Map<String, Object> response = new HashMap<>();

		// Establecimiento de condiciones para ver si hay fallos
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(e -> "El campo " + e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());

			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (clienteActual == null) {
			response.put("mensaje",
					"El cliente con id : ".concat(id.toString()).concat(" no se pudo editar en la BBDD"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setFecha(cliente.getFecha());

			clienteActualizado = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			// Controlamos con un try catch los posibles fallos.
			response.put("mensaje", "Error al crear un nuevo cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado");
		response.put("cliente", clienteActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	/**
	 * Método para borrar el cliente
	 * 
	 * @param id
	 */
	public ResponseEntity<?> delete(@PathVariable Long id) {

		// Creación de una colección del tipo Map
		Map<String, Object> response = new HashMap<>();

		try {

			clienteService.delete(id);

		} catch (DataAccessException e) {

			// Controlamos con un try catch los posibles fallos.
			response.put("mensaje", "Error al eliminar un cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	/**
	 * Método para contar las páginas de la aplicación
	 * @param page
	 * @return
	 */
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		return clienteService.findAll(PageRequest.of(page, 1));
	}

}
