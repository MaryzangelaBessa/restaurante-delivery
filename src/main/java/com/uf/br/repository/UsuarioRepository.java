package com.uf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uf.br.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
