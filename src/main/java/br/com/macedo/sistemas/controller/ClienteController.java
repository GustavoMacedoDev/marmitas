package br.com.macedo.sistemas.controller;


import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.Endereco;
import br.com.macedo.sistemas.domain.PessoaJuridica;
import br.com.macedo.sistemas.dto.CadastroPjDto;
import br.com.macedo.sistemas.dto.ClienteBuscaDto;
import br.com.macedo.sistemas.dto.ClienteDTO;
import br.com.macedo.sistemas.dto.ClienteNewDto;
import br.com.macedo.sistemas.services.ClienteService;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public List<Cliente> listaClientes() {
		
		return this.clienteService.findAll();
		
	}
	
	@RequestMapping(value = "/cadastrar-pf",method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto objDto) {
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/*@RequestMapping(value="/telefone", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@RequestParam(value="value") String telefone) {
		Cliente obj = clienteService.findByTelefone(telefone);
		return ResponseEntity.ok().body(obj);
	}*/
	
	@RequestMapping(value = "/clientes", method=RequestMethod.GET)
	public ClienteBuscaDto findAll(@RequestParam(value="value") String telefone) {
		Cliente cliente = clienteService.findByTelefone(telefone);
		ClienteBuscaDto clienteBuscaDto = new ClienteBuscaDto();
		List<Endereco> endereco = cliente.getEnderecos();
		for(Endereco end: endereco) {
			clienteBuscaDto.setComplemento(end.getComplemento());
			clienteBuscaDto.setBairro(end.getBairro());
			clienteBuscaDto.setLogradouro(end.getLogradouro());
			clienteBuscaDto.setNumero(end.getNumero());
		}
		clienteBuscaDto.setId(cliente.getId());
		clienteBuscaDto.setNome(cliente.getNome());
		clienteBuscaDto.setTelefone(cliente.getTelefone());
		
		return clienteBuscaDto;
	}
	
	private ClienteNewDto converterClienteNewDto(Cliente cliente) {
		ClienteNewDto clienteNewDto = new ClienteNewDto();
		clienteNewDto.setNome(cliente.getNome());
		clienteNewDto.setTelefone(cliente.getTelefone());
		//clienteNewDto.setBairro(cliente.get);

		return clienteNewDto;
	}

}
