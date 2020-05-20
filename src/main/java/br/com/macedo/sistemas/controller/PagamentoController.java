package br.com.macedo.sistemas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.macedo.sistemas.domain.Pagamento;
import br.com.macedo.sistemas.dto.PagamentoDto;
import br.com.macedo.sistemas.services.PagamentoService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;

	
	@RequestMapping(value = "/pagamento", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Validated @RequestBody PagamentoDto pagamentoDto ) {
		
		System.out.println(pagamentoDto.getCliente());
		
		Pagamento pagamento = this.pagamentoService.insertPagamentoMesa(pagamentoDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pagamento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}
	
	@RequestMapping(value = "/pagamentos", method = RequestMethod.GET)
	public @ResponseBody List<Pagamento> listaPagamentos() {
		return this.pagamentoService.findAll();
	}
	
	@RequestMapping(value = "/pagamento/mesa/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Pagamento> findByMesa(@Validated @PathVariable Integer id) {
		return this.pagamentoService.findByIdMesa(id);
	}
	
	@RequestMapping(value = "/pagamento/entrega/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Pagamento> findByEntrega(@Validated @PathVariable Integer id) {
		return this.pagamentoService.findByIdPedido(id);
	}

}






