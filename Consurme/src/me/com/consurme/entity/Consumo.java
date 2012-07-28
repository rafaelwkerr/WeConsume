package me.com.consurme.entity;

import java.io.Serializable;

public class Consumo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private long id_consumo;
	
	private long id_categoria;
	
	private Double valor_consumo;
	
	
	public long getId_consumo() {
		return id_consumo;
	}
	public void setId_consumo(long id_consumo) {
		this.id_consumo = id_consumo;
	}
	public long getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(long id_categoria) {
		this.id_categoria = id_categoria;
	}
	public Double getValor_consumo() {
		return valor_consumo;
	}
	public void setValor_consumo(Double valor_consumo) {
		this.valor_consumo = valor_consumo;
	}
	
	
	public Consumo() {
		super();
		
	}
	
	public Consumo(long id_consumo, long id_categoria, Double valor_consumo) {
		super();
		this.id_consumo = id_consumo;
		this.id_categoria = id_categoria;
		this.valor_consumo = valor_consumo;
	}
	
	
	public Consumo(long id_categoria, Double valor_consumo) {
		super();
		this.id_categoria = id_categoria;
		this.valor_consumo = valor_consumo;
	}	
	
	

}
