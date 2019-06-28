package com.uf.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uf.br.service.PratoService;

@Controller
public class IndexController {

	@Autowired
	private PratoService pratoService;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("Index");
		mv.addObject("pratos", pratoService.read());
		return mv;
	}
}
