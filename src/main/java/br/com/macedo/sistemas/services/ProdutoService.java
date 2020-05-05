package br.com.macedo.sistemas.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.macedo.sistemas.domain.Produto;
import br.com.macedo.sistemas.dto.ProdutoNewDto;
import br.com.macedo.sistemas.exceptions.ObjectNotFoundException;
import br.com.macedo.sistemas.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	public List<Produto> findAllAtivos(int status) {
		return this.produtoRepository.findAllByStatus(status);
	}
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public List<Produto> findByCategoria(Integer id) {
		List<Produto> produtos = produtoRepository.findByCategoriaId(id);
		
		return produtos;
	}
	
	public Produto insert(ProdutoNewDto produtoNewDto) {
		Produto produto = new Produto();
		System.out.println(produtoNewDto.getRestaurante());
		produto.setNome(produtoNewDto.getNome());
		produto.setPreco(produtoNewDto.getPreco());
		produto.setCategoria(categoriaService.find(produtoNewDto.getCategoria()));
		produto.setPessoaJuridica(pessoaJuridicaService.findByIdPj(produtoNewDto.getRestaurante()));
		
		produtoRepository.save(produto);
		
		return produto;
	}

	public @Valid Produto update(Produto produto) {
		
		this.produtoRepository.save(produto);
		
		return produto;
	}

	public List<Produto> findAll() {
		return this.produtoRepository.findAll();
	}

	public Produto inativa(Integer id) {
		
		Produto produto = this.produtoRepository.inativaProduto(id);
		
		return produto;
		
	}

	public List<Produto> getProdutosQuiosque() {
		return this.produtoRepository.findAllQuiosque();
	}

	public List<Produto> getProdutosEntrega() {
		return this.produtoRepository.findAllEntrega();
	}

}















