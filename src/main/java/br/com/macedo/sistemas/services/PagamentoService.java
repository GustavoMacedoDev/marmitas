package br.com.macedo.sistemas.services;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Cliente;
import br.com.macedo.sistemas.domain.FormaPagamento;
import br.com.macedo.sistemas.domain.Pagamento;
import br.com.macedo.sistemas.dto.PagamentoDto;
import br.com.macedo.sistemas.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private FormaPagamentoService fpService;
	
	@Autowired
	private MesaService mesaService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Pagamento insertPagamentoMesa(PagamentoDto pagamentoDto) {
		
		String formaPagamento = pagamentoDto.getfPagamento();
		FormaPagamento fPagamento =  fpService.find(Integer.parseInt(formaPagamento));
		Cliente cliente = clienteService.find(Integer.parseInt(pagamentoDto.getCliente()));
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(null);
		pagamento.setInstante(new Date());
		pagamento.setFormaPagamento(fPagamento);
		pagamento.setValorPago(pagamentoDto.getValorPago());
		pagamento.setMesa(pagamentoDto.getMesa());
		pagamento.setCliente(cliente);

		
		this.pagamentoRepository.save(pagamento);
		
		atualizaValorMesa(pagamento);
		
		return pagamento;
		
	}


	private void atualizaValorMesa(Pagamento pagamento) {
		this.mesaService.inserePagamentoMesa(pagamento);
		
	}


	public List<Pagamento> findAll() {
		return this.pagamentoRepository.findAll();
	}


	public List<Pagamento> findByIdMesa(@Valid Integer id) {
		return this.pagamentoRepository.findByMesaId(id);
	}
	
	public List<Pagamento> findByIdPedido(@Valid Integer id) {
		return this.pagamentoRepository.findByPedidoIdPedido(id);
	}


	public void encerraPagamento(Pagamento pagamento) {
		List<Pagamento> pagamentos = this.pagamentoRepository.findByMesaId(pagamento.getMesa().getId());
		
		int status = 1;
		
		
		pagamentos.forEach(pag -> {
			pag.setStatus(status);
			this.pagamentoRepository.save(pag);
		});
		
		
	}
	
	
	
}
