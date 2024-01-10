package com.desafioKSI.listafilmes;

import com.fasterxml.jackson.annotation.JsonProperty;
/*
 * Esta classe é responsável por processar e armazenar os dados retirados 
 * do Json provido pela API do "http://www.omdbapi.com/", transferindo-os 
 * para um novo objeto da classe Filme. A anotação @JsonProperty mapeia
 * uma propriedade Json para um atributo de classe Java.
 */
public class FilmeResultDTO {
	@JsonProperty("Title")
	private String Title;
	@JsonProperty("Year")
	private String Year;
	@JsonProperty("Director")
	private String Director;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	
		
}
