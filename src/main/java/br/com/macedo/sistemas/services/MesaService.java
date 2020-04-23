package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Mesa;
import br.com.macedo.sistemas.dto.MesaDto;
import br.com.macedo.sistemas.repository.MesaRepository;

@Service
public class MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	public List<Mesa> findAll() {
		return this.mesaRepository.findAll();
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
}
