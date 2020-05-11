package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Mesa;
import br.com.macedo.sistemas.domain.Pagamento;
import br.com.macedo.sistemas.domain.Pedido;
import br.com.macedo.sistemas.dto.MesaDto;
import br.com.macedo.sistemas.repository.MesaRepository;

@Service
public class MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	public List<Mesa> findAll() {
		return this.mesaRepository.findAll();
	}
	
	public Optional<Mesa> buscaMesaPorId(Integer id) {
		return this.mesaRepository.findById(id);
		
	}

	public @Valid Mesa insert(@Valid MesaDto mesaDto) {
		Mesa mesa = new Mesa();
		mesa.setId(null);
		mesa.setNumeroMesa(mesaDto.getNumeroMesa());
		mesa.setCodigoMesa(gerCodigoAleatorio());
		
		mesaRepository.save(mesa);
		
		return mesa;
	}
	
	public int gerCodigoAleatorio(){
		Random random = new Random();
		int codigoAleatorio = random.nextInt(100000);
		return codigoAleatorio;
	}

	public void atualizaPagamentosMesa(Pedido obj) {
		Mesa mesa = obj.getMesa();
		mesa = mesaRepository.getOne(mesa.getId());
		
		double totalMesa = mesa.getTotalMesa();
		double novoValor = 0;
		
		double totalAtual = obj.getTotalPedido();
		
		novoValor = totalMesa + totalAtual;
		
		mesa.setTotalMesa(novoValor);
		
		mesaRepository.save(mesa);
		
	}

	public void inserePagamentoMesa(Pagamento pagamento) {
		Mesa mesa = pagamento.getMesa();
		mesa = mesaRepository.getOne(mesa.getId());
		
		double totalParcial = mesa.getValorPagoParcial();
		double novoValor = 0;
		
		double valorPagoParcial = pagamento.getValorPago();
		
		novoValor = totalParcial + valorPagoParcial;
		
		double totalMesa = mesa.getTotalMesa();
		
		if(totalMesa == novoValor) {
			fechaPedidos(mesa);
			mesa.setValorPagoParcial(0);
			mesa.setTotalMesa(0);
			novoValor = 0;
		}
		
		mesa.setValorPagoParcial(novoValor);
		
		mesaRepository.save(mesa);
		
	}

	private void fechaPedidos(Mesa mesa) {
		this.pedidoService.fechaPedidos(mesa);
		
	}
}
