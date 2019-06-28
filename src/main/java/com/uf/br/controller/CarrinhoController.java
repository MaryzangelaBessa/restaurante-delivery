package com.uf.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uf.br.exception.SemPratosSuficientesException;
import com.uf.br.model.Prato;
import com.uf.br.repository.PratoRepository;
import com.uf.br.service.CarrinhoService;
import com.uf.br.service.PratoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carinhoService;

	@Autowired
	private PratoRepository pratoRepository;

	@Autowired
	private PratoService pratoService;

	@GetMapping
	public ModelAndView carrinho() {
		ModelAndView modelAndView = new ModelAndView("Carrinho");
		modelAndView.addObject("pratos", carinhoService.getPratosCarrinho());
		modelAndView.addObject("total", carinhoService.getTotal().toString());
		return modelAndView;
	}

	@GetMapping("/adicionar/{id}")
	public ModelAndView adicionar(@PathVariable("id") Long id) {
		pratoRepository.findById(id).ifPresent(carinhoService::adicionar);
		return carrinho();
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Prato prato = pratoService.findById(id);
		carinhoService.remover(prato);
		return carrinho();
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizar() {
		try {
			carinhoService.finalizar();
		} catch (SemPratosSuficientesException e) {
			return carrinho().addObject("Mensagem falta de estoque", e.getMessage());
		}

		// Lógica ao finalizar o carrinho
		// Criar pedido (id, id do usuario, lista de pratos, total)...
		// Persistir pedido

		return carrinho();
	}
}