package br.com.macedo.sistemas.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public Pagamento insertPagamentoMesa(PagamentoDto pagamentoDto) {
		
		String formaPagamento = pagamentoDto.getfPagamento();
		FormaPagamento fPagamento =  fpService.find(Integer.parseInt(formaPagamento));
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(null);
		pagamento.setFormaPagamento(fPagamento);
		pagamento.setValorPago(pagamentoDto.getValorPago());
		pagamento.setMesa(pagamentoDto.getMesa());

		
		this.pagamentoRepository.save(pagamento);
		
		verificaMesa(pagamento);
		
		return pagamento;
		
	}


	private void verificaMesa(Pagamento pagamento) {
		
		List<Pagamento> pagamentos = this.pagamentoRepository.findByMesaId(pagamento.getMesa().getId());
		
		double total = 0;
		for(Pagamento pags: pagamentos) {
			double valor = pags.getValorPago();
			total += valor;
		}
		
		System.out.println(total);
		
	}


	public List<Pagamento> findAll() {
		return this.pagamentoRepository.findAll();
	}


	public List<Pagamento> findByIdMesa(@Valid Integer id) {
		return this.pagamentoRepository.findByMesaId(id);
	}

}
