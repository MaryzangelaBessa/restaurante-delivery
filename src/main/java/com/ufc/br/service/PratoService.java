package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;

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

	public void remove(Long id) {
		pratoRepository.deleteById(id);
	}
}
