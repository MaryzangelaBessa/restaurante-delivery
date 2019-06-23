package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void create(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> read() {
		return usuarioRepository.findAll();
	}

	public void remove(Long id) {
		usuarioRepository.deleteById(id);
	}

}
