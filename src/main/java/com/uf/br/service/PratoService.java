package com.uf.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uf.br.model.Prato;
import com.uf.br.repository.PratoRepository;
import com.uf.br.util.FileUtil;

@Service
public class PratoService {

	@Autowired
	private PratoRepository pratoRepository;

	public void create(Prato prato, MultipartFile imagem) {
		if (!imagem.isEmpty()) {
			// System.out.println("NOME PRATO: " + prato.getNome());
			String caminho = "images/" + prato.getNome() + ".png";
			FileUtil.salvarImagem(caminho, imagem);
		}

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
