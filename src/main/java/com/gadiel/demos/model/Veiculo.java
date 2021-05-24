package com.gadiel.demos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long usuarioId;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String ano;
	
	@Column(nullable = false)
	private String valor;
	
	public Veiculo() {}

	public Veiculo(Long usuarioId, String marca, String modelo, String ano, String valor) {
		super();
		this.usuarioId = usuarioId;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}
	
	public Veiculo(Long id, Long usuarioId, String marca, String modelo, String ano, String valor) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getAno() {
		return ano;
	}

	public String getValor() {
		return valor;
	}
}
