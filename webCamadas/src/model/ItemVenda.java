package model;

import java.io.Serializable;

public class ItemVenda implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int seq;
	private Venda venda;
	Produto produto;
	int qtdProduto;
	double precoItem;
	
	public ItemVenda() {
		
	}
	
	public ItemVenda(Produto produto, int qtd, Venda venda) {
		this.produto = produto;
		this.qtdProduto = qtd;
		this.venda = venda;
	}
	
    public double getPrecoItem() {
		return precoItem;
	}

	public void setPrecoItem(double precoItem) {
		this.precoItem = precoItem;
	}

	public void updatePrecoTotal() {
        precoItem = produto.getPreco() * qtdProduto;
    }

    public void addQtd() {
        this.qtdProduto++;
    }

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public int getIdVenda() {
		return getIdVenda();
	}


	public Produto getProduto() {
		return produto;
	}
	
	public int getIdProduto() {
		return produto.getId();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	
	


	
	
}
