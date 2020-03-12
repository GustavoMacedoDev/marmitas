package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.model.ResponseModel;
import br.com.macedo.sistemas.repository.ClienteRepository;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/service")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public @ResponseBody List<Cliente> getClientes() {
		
		return this.clienteRepository.findAll();
		
	}
	
	
	@RequestMapping(value="/cliente", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody Cliente cliente){
				
		
		try {
			
			this.clienteRepository.save(cliente);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}

}
