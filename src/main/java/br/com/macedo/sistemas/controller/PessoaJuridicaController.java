package br.com.macedo.sistemas.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.macedo.sistemas.domain.PessoaJuridica;
import br.com.macedo.sistemas.dto.CadastroPjDto;
import br.com.macedo.sistemas.model.Response;
import br.com.macedo.sistemas.services.PessoaJuridicaService;
import br.com.macedo.sistemas.services.PjService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class PessoaJuridicaController {

	private static final Logger log = LoggerFactory.getLogger(PessoaJuridicaController.class);


	@Autowired
	private PjService pjService;
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	
	@RequestMapping(value = "/pj", method = RequestMethod.GET)
	public @ResponseBody List<PessoaJuridica> findAll() {
		return this.pessoaJuridicaService.findAll();
	}
	
	@RequestMapping(value = "/pj/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<PessoaJuridica> find(@PathVariable Integer id) {
		Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.find(id);
		
		return pessoaJuridica;
	}
	

	@PostMapping(value = "/cadastrar-pj")
	public ResponseEntity<Response<CadastroPjDto>> cadastrar(@Valid @RequestBody CadastroPjDto cadastroPjDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PJ: {}", cadastroPjDto.toString());
		Response<CadastroPjDto> response = new Response<CadastroPjDto>();

		validarDadosExistentes(cadastroPjDto, result);
		PessoaJuridica pessoaJuridica = this.converterDtoParaPj(cadastroPjDto);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.pjService.persistir(pessoaJuridica);

		return ResponseEntity.ok(response);
	}

	private void validarDadosExistentes(CadastroPjDto cadastroPJDto, BindingResult result) {
		this.pjService.buscarPorCnpj(cadastroPJDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

	}

	private PessoaJuridica converterDtoParaPj(CadastroPjDto cadastroPjDto) {
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj(cadastroPjDto.getCnpj());
		pessoaJuridica.setRazaoSocial(cadastroPjDto.getRazaoSocial());

		return pessoaJuridica;
	}
	
	
}