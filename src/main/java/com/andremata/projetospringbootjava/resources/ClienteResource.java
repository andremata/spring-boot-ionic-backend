package com.andremata.projetospringbootjava.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andremata.projetospringbootjava.domain.Cliente;
import com.andremata.projetospringbootjava.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultar(@PathVariable Integer id) {
		Cliente cliente = service.consultar(id);
		
		return ResponseEntity.ok().body(cliente);
	}
}
