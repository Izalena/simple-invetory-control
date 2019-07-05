package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;
import dao.FabricaConexao;

public class ProdutoDAO {

	FabricaConexao conexao;

	public ProdutoDAO() {
		this.conexao = new FabricaConexao();
	}

	public void inserir(Produto produto) throws SQLException {
		String SQL = "insert into produto ( id_categoria, id_marca,nome_produto, descricao_produto, preco_produto, url_img) values (?, ?, ?, ?, ?, ?)";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		ps.setInt(1, produto.getCategoria().getId());
		ps.setString(3, produto.getNome());
		ps.setString(4, produto.getDescricao());
		ps.setDouble(5, produto.getPreco());
		ps.setString(6, produto.getImgUrl());

		ps.execute();

	}

	public List<Produto> listarTodos() throws SQLException {
		List<Produto> produtos = new ArrayList<Produto>();
		ResultSet rsProduto = null;
		ResultSet rsCategoria = null;
		ResultSet rsMarca = null;

		String produtoSQL = "select id_produto, id_categoria, id_marca, nome_produto, descricao_produto, preco_produto, url_img from produto";
		String categoriaSQL = "select id_categoria ,nome_categoria, descricao_categoria from categoria where id_categoria = ?";
		String marcaSQL =  "select id_marca, nome_marca, descricao_marca from marca where id_marca = ?";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement psProduto = connector.prepareStatement(produtoSQL);
		PreparedStatement psCategoria = connector.prepareStatement(categoriaSQL);
		PreparedStatement psMarca = connector.prepareStatement(marcaSQL);;

		

		rsProduto = psProduto.executeQuery();
		
		
		while (rsProduto.next()) {

			Produto e = new Produto();
			e.setId(rsProduto.getInt(1));

			
			psCategoria.setInt(1, rsProduto.getInt(2));
			psMarca.setInt(1, rsProduto.getInt(3));
			
			rsCategoria = psCategoria.executeQuery();
			rsMarca = psCategoria.getResultSet();
			
			while(rsCategoria.next()) {
				if (e.getCategoria() != null) {
					e.setCategoria(new Categoria(rsCategoria.getInt(1), rsCategoria.getString(2), rsCategoria.getString(3)));
				}
			}

			
			e.setNome(rsProduto.getString(4));
			e.setDescricao(rsProduto.getString(5));
			e.setPreco(rsProduto.getDouble(6));
			e.setImgUrl(rsProduto.getString(7));

			produtos.add(e);

		}

		return produtos;
	}

	public Boolean Excluir(int _id) throws SQLException {
		String SQL = "delete from produto where id_produto = ?";

        Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		ps.setInt(1, _id);

		ps.execute();
		return true;
	}

	public Boolean Atualizar(Produto _objeto) throws SQLException {
		String sql = "UPDATE produto SET nome_produto = ?";
        sql += " WHERE id_produto = ?";
        Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(sql);
		ps.setString(1, _objeto.getNome());
		ps.setInt(2, _objeto.getId());
		         
        boolean rowUpdated = ps.executeUpdate() > 0;
        conexao.fecharConexao();
        return rowUpdated;     
	}

	public Produto buscarPorId(int _id) throws SQLException {
		ResultSet rsProduto = null;
		ResultSet rsCategoria = null;
		ResultSet rsMarca = null;

		String SQL = "select id_produto, id_categoria, id_marca,nome_produto, descricao_produto, preco_produto, url_img from produto where id_produto = ?";
		String categoriaSQL = "select id_categoria ,nome_categoria, descricao_categoria from categoria where id_categoria = ?";
		String marcaSQL =  "select id_marca, nome_marca, descricao_marca from marca where id_marca = ?";
		
		
		
		
        Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);
		PreparedStatement psCategoria = connector.prepareStatement(categoriaSQL);
		PreparedStatement psMarca = connector.prepareStatement(marcaSQL);;
		ps.setInt(1, _id);

		rsProduto = ps.executeQuery();

		Produto e = new Produto();
		if (rsProduto.next()) {

			e.setId(rsProduto.getInt(1));

			
			psCategoria.setInt(1, rsProduto.getInt(2));
			psMarca.setInt(1, rsProduto.getInt(3));
			
			rsCategoria = psCategoria.executeQuery();
			rsMarca = psCategoria.getResultSet();
			
			while(rsCategoria.next()) {
				if (e.getCategoria() != null) {
					e.setCategoria(new Categoria(rsCategoria.getInt(1), rsCategoria.getString(2), rsCategoria.getString(3)));
				}
			}
			
			e.setNome(rsProduto.getString(4));
			e.setDescricao(rsProduto.getString(5));
			e.setPreco(rsProduto.getDouble(6));
			e.setImgUrl(rsProduto.getString(7));
		}
		return e;

	}

}