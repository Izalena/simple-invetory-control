package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private double valorTotal;
	private Date dataVenda;
	private Cliente cliente;
	private int total = 0;

	private ArrayList<ItemVenda> itens;
	private String situacao;

	public Venda() {
		this.itens = new ArrayList<>();
		this.valorTotal = 0;
		this.situacao = "CARRINHO";

	}

	public Venda(int id, double valorTotal, Date dataVenda, Cliente cliente, ArrayList<ItemVenda> itens,
			String situacao) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataVenda = dataVenda;
		this.cliente = cliente;
		this.itens = itens;
		this.situacao = situacao;
	}

	public boolean adicionaItem(Produto produto) {
		if (produto == null)
			return false;

		for (ItemVenda item : itens) {
			if (item.getIdProduto() == produto.getId()) {
				item.addQtd();
				item.updatePrecoTotal();
				return true;
			}
		}
		ItemVenda newItem = new ItemVenda(produto, 1, this);
		itens.add(newItem);
		atualizaValorCarrinho();
		return true;
	}

	private void atualizaValorCarrinho() {
		this.valorTotal = 0.00;
		this.total = 0;
		if (itens.size() > 0) {
			for (ItemVenda item : itens) {
				this.valorTotal += item.getPrecoItem();
				this.total += item.getQtdProduto();
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getQtdProdutos() {
		return this.getItens().size();
	}

}
