package com.uf.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.br.model.Prato;
import com.uf.br.repository.PratoRepository;

@Service
public class PratoService {

	@Autowired
	private PratoRepository pratoRepository;

	public void create(Prato prato) {
		pratoRepository.save(prato);
	}

	public List<Prato> read() {
		return pratoRepository.findAll();
	}

	public void delete(Long id) {
		pratoRepository.deleteById(id);
	}

	public Prato findById(Long id) {
		return pratoRepository.getOne(id);
	}
}
