package com.desafioKSI.listafilmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Esta é a classe main do projeto, responsável por inicializar o projeto como um todo, 
 * buscando todos os Beans (classes com anotações @Service, @Controller, @RestController, @Component, etc.).
 */
@SpringBootApplication
public class ListafilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListafilmesApplication.class, args);
	}

}
