package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.TaxaEntrega;
import br.com.macedo.sistemas.dto.TaxaEntregaDto;
import br.com.macedo.sistemas.services.TaxaEntregaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TaxaEntregaController {
	
	@Autowired
	private TaxaEntregaService txService;
	
	@RequestMapping(value = "/taxas", method = RequestMethod.GET)
	public @ResponseBody List<TaxaEntrega> findAll() {
		return this.txService.findAll();
		
	}
	
	@PostMapping(value = "/taxa")
	public ResponseEntity<Void> insert(@Validated @RequestBody TaxaEntregaDto taxaEntregaDto) {
		
		TaxaEntrega taxaEntrega = txService.insert(taxaEntregaDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(taxaEntrega.getId()).toUri();
			 ResponseEntity.created(uri).build();
			
			return ResponseEntity.created(uri).build();
		
	}

}
