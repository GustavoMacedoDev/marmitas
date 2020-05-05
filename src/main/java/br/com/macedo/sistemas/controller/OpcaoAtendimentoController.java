package br.com.macedo.sistemas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.OpcaoAtendimento;
import br.com.macedo.sistemas.services.OpcaoAtendimentoService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OpcaoAtendimentoController {
	
	@Autowired
	private OpcaoAtendimentoService opService;
	
	
	@RequestMapping(value = "/opatendimentos", method = RequestMethod.GET)
	public @ResponseBody List<OpcaoAtendimento> findAll() {
		return this.opService.findAll();
	}
	
	
}
