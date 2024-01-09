package com.desafioKSI.listafilmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import consultaFilme.FilmeResultDTO;

@Controller
public class FilmeController {
	@Autowired
	private FilmeService service;
	
	
	@GetMapping("/")
	public ModelAndView viewHomePage() {
		ModelAndView mav = new ModelAndView("index");
		List<Filme> listaFilmes = service.listAll();
		mav.addObject("listaFilmes", listaFilmes);
		mav.addObject("filmeEscolhido", new FilmeResultDTO());
		return mav;
	}
	
	@PostMapping(value = "/salvar")
	public String salvarFilme(@ModelAttribute("filme") Filme filme) {
		service.save(filme);
		
		return "redirect:/";
	}
	
	@GetMapping("/editar_filme/{id}")
	public ModelAndView mostrarPaginaEditarFilme(@PathVariable(name = "id") Long id) {
	    ModelAndView mav = new ModelAndView("editar_filme");
	    Filme filme = service.get(id);
	    mav.addObject("filme", filme);
	     	
	    return mav;
	}
	
	@GetMapping("/apagar_filme/{id}")
	public String apagarFilme(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
	@PostMapping(value = "/pesquisarFilme")
	public String pesquisarFilme(@ModelAttribute("filmeEscolhido") FilmeResultDTO filmeResult) {
		service.buscarFilmePorTitulo(filmeResult.getTitle());
		return "redirect:/";
	}		
}
