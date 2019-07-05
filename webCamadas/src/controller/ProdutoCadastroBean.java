package controller;

import org.primefaces.model.chart.*;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Categoria;
import model.Produto;
import dao.ProdutoDAO;

@javax.annotation.ManagedBean
public class ProdutoCadastroBean {
	
	private PieChartModel pieModel;
	private int id;
	private Categoria categoria;
	private String nome;
	private String descricao;
	private double preco;
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto = new Produto();
	
	private boolean sucesso = false;
	
	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
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

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public void gravar() throws Exception {
		System.out.println("Salvando produto: " + this.produto.getNome());

		try {
			produto = this.produtoDAO.buscarPorId(this.getId());
			if (produto != null) {
				throw new RuntimeException("produto com esse id já existe.");
			}

			this.produtoDAO.inserir(this.produto);
			System.out.println("produto salvo" + this.produto.getNome() + "com sucesso!");
			this.sucesso = true;

		} catch (Exception e) {
			throw e;
		}
	}

	
	public void graficar(List<Produto> lista) {
		pieModel = new PieChartModel();
		
		for(Produto pro : lista){
			pieModel.set(pro.getNome(), pro.getPreco());
		}
		
		pieModel.setTitle("Preços");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
	}

}

