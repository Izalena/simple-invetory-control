package model;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Categoria categoria;
	private String nome;
	private String descricao;
	private double preco;
	private String url_img;
	
	public String getImgUrl() {
		return url_img;
	}

	public void setImgUrl(String imgUrl) {
		this.url_img = imgUrl;
	}

	public Produto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Produto(Categoria categoria, String nome, String descricao, double preco) {
		super();
		this.categoria = categoria;		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	

}