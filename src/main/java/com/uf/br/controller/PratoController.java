package com.uf.br.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@RequestMapping("/prato")
@Controller
public class PratoController {

	PratoService pratoService = new PratoService();

	@GetMapping("/cadastro")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("cadastroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView read() {
		List<Prato> pratos = pratoService.read();
		ModelAndView mv = new ModelAndView("listarPratos");
		mv.addObject("pratos", pratos);
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView save(@Validated Prato prato, BindingResult result) {
		ModelAndView mv = new ModelAndView("cadastroPrato");

		if (result.hasErrors())
			return mv;
		pratoService.create(prato);
		mv.addObject("mensagem", "Pessoa cadastrada com Sucesso!");
		return mv;
	}
}
