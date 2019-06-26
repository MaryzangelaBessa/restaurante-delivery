package com.uf.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uf.br.model.Usuario;
import com.uf.br.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/cadastro")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView read() {
		List<Usuario> usuarios = usuarioService.read();
		ModelAndView mv = new ModelAndView("ListarUsuarios");
		mv.addObject("usuarios", usuarios);
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView save(@Validated Usuario usuario, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastroUsuario");

		if (result.hasErrors())
			return mv;
		usuarioService.create(usuario);
		mv.addObject("mensagem", "Pessoa cadastrada com Sucesso!");
		return mv;
	}

	@DeleteMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		usuarioService.delete(id);
		ModelAndView mv = new ModelAndView("redirect: /usuario/listar");
		return mv;
	}

	@PutMapping("/atualizar/{id}")
	public ModelAndView update(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		ModelAndView mv = new ModelAndView("CadastroPratos");
		mv.addObject("usuario", usuario);
		return mv;
	}
}
