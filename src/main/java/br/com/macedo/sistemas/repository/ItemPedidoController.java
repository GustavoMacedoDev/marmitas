package br.com.macedo.sistemas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.ItemPedido;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/service")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	@RequestMapping(value="/pedido/{idPedido}", method = RequestMethod.GET)
	public @ResponseBody List<ItemPedido> buscar(@PathVariable("idPedido") Integer idPedido){
		
		return this.itemPedidoRepository.buscaPedido(idPedido);
		
		
		
	}
	
}
