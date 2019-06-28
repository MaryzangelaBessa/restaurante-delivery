package com.uf.br.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.uf.br.exception.SemPratosSuficientesException;
import com.uf.br.model.Prato;
import com.uf.br.repository.PratoRepository;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CarrinhoService {

	// Como utilizar o HashMap:
	// https://www.devmedia.com.br/hashmap-java-trabalhando-com-listas-key-value/29811

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private PratoService pratoService;

	private Map<Prato, Integer> pratos = new HashMap<>();

	public void adicionar(Prato prato) {
		if (pratos.containsKey(prato)) {
			pratos.replace(prato, pratos.get(prato) + 1);
		} else {
			pratos.put(prato, 1);
		}
	}

	public void remover(Prato prato) {
		if (pratos.containsKey(prato)) {
			if (pratos.get(prato) > 1)
				pratos.replace(prato, pratos.get(prato) - 1);
			else if (pratos.get(prato) == 1) {
				pratos.remove(prato);
			}
		}
	}

	public Map<Prato, Integer> getPratosCarrinho() {
		return Collections.unmodifiableMap(pratos);
	}

	public void finalizar() throws SemPratosSuficientesException {
		Prato prato;
		for (Map.Entry<Prato, Integer> entry : pratos.entrySet()) {
			prato = pratoService.findById(entry.getKey().getId());
			if (prato.getQuantidade() < entry.getValue())
				throw new SemPratosSuficientesException(prato);
			entry.getKey().setQuantidade(prato.getQuantidade() - entry.getValue());
		}
		pratoRepository.saveAll(pratos.keySet());
		// Descubra...
		// pratoRepository.flush();
		// pratoRepository.clear();
	}

	public BigDecimal getTotal() {
		return pratos.entrySet().stream()
				.map(entry -> entry.getKey().getPreco().multiply(BigDecimal.valueOf(entry.getValue())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}