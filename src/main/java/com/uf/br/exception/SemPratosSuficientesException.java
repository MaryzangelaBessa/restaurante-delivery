package com.uf.br.exception;

import com.uf.br.model.Prato;

@SuppressWarnings("serial")
public class SemPratosSuficientesException extends Exception {

	private static final String DEFAULT_MESSAGE = "Sem pratos suficientes.";

	public SemPratosSuficientesException() {
		super(DEFAULT_MESSAGE);
	}

	public SemPratosSuficientesException(Prato prato) {
		super(String.format("Sem pratos %s suficientes. Apenas %d restantes.", prato.getNome(), prato.getQuantidade()));
	}
}