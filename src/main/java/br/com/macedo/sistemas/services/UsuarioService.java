package br.com.macedo.sistemas.services;

import java.util.Optional;

import br.com.macedo.sistemas.domain.Usuario;

public interface UsuarioService {

	Optional<Usuario> buscarPorEmail(String email);
}
