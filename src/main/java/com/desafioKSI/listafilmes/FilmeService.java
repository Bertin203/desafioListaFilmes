package com.desafioKSI.listafilmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import consultaFilme.FilmeResultDTO;

@Service
public class FilmeService {
	@Autowired
	private FilmeRepository repo;
	
	
	public List<Filme> listAll() {
        return repo.findAll();
    }
     
    public void save(Filme filme) {
        repo.save(filme);
    }
     
    public Filme get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
    
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
