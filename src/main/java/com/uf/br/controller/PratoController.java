package com.uf.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uf.br.model.Prato;
import com.uf.br.service.PratoService;

@Controller
@RequestMapping("/prato/")
public class PratoController {

	@Autowired
	private PratoService pratoService;

	@GetMapping("/cadastro")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("CadastroPrato");
		mv.addObject("prato", new Prato());
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView read() {
		List<Prato> pratos = pratoService.read();
		ModelAndView mv = new ModelAndView("ListarPratos");
		mv.addObject("pratos", pratos);
		return mv;
	}

	@PostMapping("/salvar")
	public String save(@Validated Prato prato, BindingResult result) {
		if (result.hasErrors())
			return "cadastro";
		pratoService.create(prato);
		return "redirect:listar";
	}

	@GetMapping("/excluir/{id}")
	public String delete(@PathVariable Long id) {
		pratoService.delete(id);
		return "ListarPratos";
	}

	@GetMapping("/atualizar/{id}")
	public ModelAndView update(@PathVariable Long id) {
		Prato prato = pratoService.findById(id);
		ModelAndView mv = new ModelAndView("CadastroPrato");
		mv.addObject("prato", prato);
		return mv;
	}
}
