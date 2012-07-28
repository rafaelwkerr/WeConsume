package me.com.consurme.entity;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;	
	private String categoria;
	private Double valorLimite;
	
	public Categoria(){
		
	}
	
	public Categoria(long id, String categoria, Double valorLimite) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.valorLimite = valorLimite;
	}


	public Categoria(String categoria, Double valorLimite) {
		super();
		this.categoria = categoria;
		this.valorLimite = valorLimite;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public Double getValorLimite() {
		return valorLimite;
	}


	public void setValorLimite(Double valorLimite) {
		this.valorLimite = valorLimite;
	}


	@Override
	public String toString() {
		return this.getCategoria();
	}


	
	

}
