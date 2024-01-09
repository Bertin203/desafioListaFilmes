package com.desafioKSI.listafilmes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/*
 * Esta classe é uma entidade, havendo uma tabela de mesmo nome no banco de dados. 
 * Por esta razão, recebe a anotação @Entity.
 * Ou seja, quando um objeto desta classe for persistido, o mesmo terá seus dados armazenados na tabela.
 */
@Entity
public class Filme {
	private Long id;
	private String titulo;
	private String ano;
	private String direcao;
	
	protected Filme() {

	}
	/*
	 * A anotação @Id serve para informar que este atributo da entidade será utilizado como chave primária 
	 * da tabela Filme no banco de dados.
	 * Já a @GeneratedValue tem como função informar que a geração do valor é gerenciada pela persistência (JPA).
	 * 
	 * O atributo "strategy" informa que a estratégia de geração da chave primária será modificada. Neste caso em
	 * específico, os valores a serem atribuídos ao identificador único serão gerados pela coluna de auto incremento do banco de dados. 
	 * Assim, um valor para o identificador é gerado para cada registro inserido no banco.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {	
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
}
