package br.com.macedo.sistemas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.TaxaEntrega;
import br.com.macedo.sistemas.dto.TaxaEntregaDto;
import br.com.macedo.sistemas.repository.TaxaEntregaRepository;

@Service
public class TaxaEntregaService {
	
	@Autowired
	private TaxaEntregaRepository txRepository;

	
	public List<TaxaEntrega> findAll() {
		return this.txRepository.findAll();
	}
	
	public TaxaEntrega insert(TaxaEntregaDto txEntregaDto) {
		TaxaEntrega taxaEntrega = new TaxaEntrega();
		taxaEntrega.setId(null);
		taxaEntrega.setDescricao(txEntregaDto.getDescricao());
		taxaEntrega.setValor(txEntregaDto.getValor());
		
		txRepository.save(taxaEntrega);
		
		return taxaEntrega;
	}
}
