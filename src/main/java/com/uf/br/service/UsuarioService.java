package com.uf.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uf.br.model.Usuario;
import com.uf.br.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void create(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
	}

	public List<Usuario> read() {
		return usuarioRepository.findAll();
	}

	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Usuario findById(Long id) {
		return usuarioRepository.getOne(id);
	}
}
