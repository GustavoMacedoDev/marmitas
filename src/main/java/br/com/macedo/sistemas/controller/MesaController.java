package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Mesa;
import br.com.macedo.sistemas.dto.MesaDto;
import br.com.macedo.sistemas.services.MesaService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MesaController {
	
	@Autowired
	private MesaService mesaService;
	
	@RequestMapping(value = "/mesas", method = RequestMethod.GET)
	public @ResponseBody List<Mesa> listaMesas() {
		return mesaService.findAll();
	}
	
	@RequestMapping(value = "/mesa/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Mesa> buscaMesaPorId(@Validated @PathVariable Integer id) {
		
		Optional<Mesa> mesa = this.mesaService.buscaMesaPorId(id);
		
		return mesa;
		
	}

	@PostMapping(value = "/mesa")
	public ResponseEntity<Void> insert(@Validated @RequestBody MesaDto mesaDto) {

		Mesa mesa = mesaService.insert(mesaDto);		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(mesa.getId()).toUri();
		 ResponseEntity.created(uri).build();
		
		return ResponseEntity.created(uri).build();
	}
}
