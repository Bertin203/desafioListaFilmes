package com.desafioKSI.listafilmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
/*
 * Esta é a classe Spring MVC Controller, por isso recebe a anotação @Controller.
 * Ela é responsável por receber requisições e enviar respostas ao usuário.
 * A anotação @Autowired na chamada da classe de serviço traz os métodos que salvam, alteram ou excluem 
 * a entidade Filme no banco de dados.
 */
@Controller
public class FilmeController {
	@Autowired
	private FilmeService service;
	
	/**
	 * Retorna a página principal da aplicação, listando os dados presentes na entidade Filme.
	 * @return "index"
	 * @author Gilberto
	 * 
	 */
	@GetMapping("/")
	public ModelAndView viewHomePage() {
		ModelAndView mav = new ModelAndView("index"); // recebe como parâmetro o caminho do arquivo HTML que exibe os dados.
		List<Filme> listaFilmes = service.listAll(); // solicita a listagem de todos os dados por meio da lista.
		mav.addObject("listaFilmes", listaFilmes); // envia a lista para a página principal.
		mav.addObject("filmeEscolhido", new FilmeResultDTO()); // busca o filme pesquisado no campo <select>
		return mav;
	}
	
	/**
	 * Retorna a página principal da aplicação, após salvar as alterações realizadas em um objeto 
	 * da classe Filme, na página "/editar_filme"
	 * @return "redirect:/"
	 * @author Gilberto
	 * 
	 */
	@PostMapping(value = "/salvar")
	public String salvarFilme(@ModelAttribute("filme") Filme filme) {
		service.save(filme);
		
		return "redirect:/";
	}
	
	/**
	 * Dirige o usuário até a página de edição dos atributos do filme desejado,
	 * buscando pelo id e exibindo os atributos nos campos de input.
	 * @return "editar_filme"
	 * @author Gilberto
	 * 
	 */
	@GetMapping("/editar_filme/{id}")
	public ModelAndView mostrarPaginaEditarFilme(@PathVariable(name = "id") Long id) {
	    ModelAndView mav = new ModelAndView("editar_filme");
	    Filme filme = service.get(id);
	    mav.addObject("filme", filme);
	     	
	    return mav;
	}
	
	/**
	 * Apaga o filme desejado, buscando pelo id e redirecionando o usuário para a página principal.
	 * @return "redirect:/"
	 * @author Gilberto
	 * 
	 */
	@GetMapping("/apagar_filme/{id}")
	public String apagarFilme(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
	/**
	 * Pesquisa o filme no campo <select> por meio do título, consumindo a API do site https://www.omdbapi.com/.
	 * Chama o método "buscarFilmePorTitulo", presente na classe de serviço. 
	 * Retorna para a página principal, apresentando os dados do filme pesquisado na tabela.
	 * @return "redirect:/"
	 * @author Gilberto
	 * 
	 */
	@PostMapping(value = "/pesquisarFilme")
	public String pesquisarFilme(@ModelAttribute("filmeEscolhido") FilmeResultDTO filmeResult) {
		service.buscarFilmePorTitulo(filmeResult.getTitle());
		return "redirect:/";
	}		
}
