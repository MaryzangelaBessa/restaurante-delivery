package com.uf.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uf.br.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

}
