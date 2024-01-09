package com.desafioKSI.listafilmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import consultaFilme.FilmeResultDTO;

/*
 * Esta é a classe de serviço. A anotação @Service marca esta classe como a responsável por conter toda
 * a lógica de negócio da aplicação. Neste caso, possibilitar as funcionalidades de listar, salvar, buscar e deletar,
 * utilizando como parâmetro o identificador único do objeto.
 */
@Service
public class FilmeService {
	@Autowired
	private FilmeRepository repo;
	
	/**
	 * Lista todos os filmes presentes no banco.
	 * @return "repo.findAll()"
	 * @author Gilberto
	 * 
	 */
	public List<Filme> listAll() {
        return repo.findAll();
    }
    
	/**
	 * Salva um novo objeto da classe Filme.
	 * @return "repo.save(filme)"
	 * @author Gilberto
	 * 
	 */
    public void save(Filme filme) {
        repo.save(filme);
    }
    
    /**
	 * Busca um filme presente no banco, pelo seu id.
	 * @return "repo.findById(id).get()"
	 * @author Gilberto
	 * 
	 */
    public Filme get(long id) {
        return repo.findById(id).get();
    }
    
    /**
	 * Apaga um filme presente no banco, pelo seu id.
	 * @return "repo.deleteById(id)"
	 * @author Gilberto
	 * 
	 */
    public void delete(long id) {
        repo.deleteById(id);
    }
    
    /**
	 * Busca um filme pelo título presente no campo <select>, consumindo a API do "http://www.omdbapi.com/" por meio
	 * da classe RestTemplate, que retorn um Json, sendo este processado pela classe ResponseEntity e tendo os dados 
	 * desejados armazenados em um objeto da classe FilmeResultDTO, que transfere os mesmos para um objeto de Filme.
	 * @return "new FilmeResultDTO()"
	 * @author Gilberto
	 * 
	 */
    public FilmeResultDTO buscarFilmePorTitulo(String title) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://www.omdbapi.com/")
                .queryParam("apikey", "3f3826e6")
                .queryParam("t", title);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FilmeResultDTO> fr = restTemplate.getForEntity(builder.build().toUri(), FilmeResultDTO.class);
        FilmeResultDTO filmeResult = fr.getBody();
        
        Filme filme = new Filme();        
        filme.setTitulo(filmeResult.getTitle());
        filme.setAno(filmeResult.getYear());
        filme.setDirecao(filmeResult.getDirector());
        
        repo.save(filme);
        return new FilmeResultDTO();
    }
}
