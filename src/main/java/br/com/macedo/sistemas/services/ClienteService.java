package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.Endereco;
import br.com.macedo.sistemas.dto.ClienteDTO;
import br.com.macedo.sistemas.dto.ClienteNewDto;
import br.com.macedo.sistemas.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.repository.ClienteRepository;
import br.com.macedo.sistemas.repository.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getTelefone());
	}
	
	public Cliente fromDTO(ClienteNewDto objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getTelefone());
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), cliente);
		cliente.getEnderecos().add(endereco);

		return cliente;
	}
	
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findByTelefone(String telefone) {
		
		Cliente obj = clienteRepository.findByTelefone(telefone);
		if (obj == null) {
			return null;
		}
		return obj;
	}
	
}
