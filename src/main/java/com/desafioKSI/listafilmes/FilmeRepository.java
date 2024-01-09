package com.desafioKSI.listafilmes;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * A classe JpaRepository fornece acesso ao banco de dados e permite a alteração de atributos 
 * sem a necessidade de escrever comandos SQL. 
 */
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
}
