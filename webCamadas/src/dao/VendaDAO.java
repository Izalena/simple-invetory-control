package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemVenda;
import model.Venda;
import dao.ProdutoDAO;
import dao.ClienteDAO;
import dao.FabricaConexao;

public class VendaDAO {

	private FabricaConexao conexao;
	
	private ClienteDAO clienteDAO;
	
	private ProdutoDAO produtoDAO;
	
	public VendaDAO(){
		this.conexao = new FabricaConexao();
	}
	

	public void Inserir(Venda _objeto) throws SQLException {
		String SQL = "insert into venda (data_venda, total_venda, id_cliente, qtd_venda) values (?, ?, ?, ?)";

        Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);
		ps.setDate(1, _objeto.getDataVenda());
		ps.setDouble(2, _objeto.getValorTotal());
		ps.setInt(3, _objeto.getCliente().getId());
		ps.setInt(4, _objeto.getQtdProdutos());

		ps.execute();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		int idVenda = rs.getInt(1);
		
		for (ItemVenda iv : _objeto.getItens()) {
			SQL = "INSERT INTO ITEMVENDA (id_venda, id_produto, qtd) VALUES (?, ?, ?)";
			ps = connector.prepareStatement(SQL);
			ps.setInt(1, idVenda);
			ps.setInt(2, iv.getIdProduto());
			ps.setInt(3, iv.getQtdProduto());

			ps.execute();
		}
		this.conexao.fecharConexao();
	}

	public List<Venda> listarTodos() throws SQLException {
		List<Venda> vendas = new ArrayList<Venda>();
		ResultSet rs = null;

		String SQL = "select id_venda, data_venda, total_venda, id_cliente, qtd_venda from venda";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		rs = ps.executeQuery();
		while (rs.next()) {

			Venda e = new Venda();
			e.setId(rs.getInt(1));
			e.setDataVenda(rs.getDate(2));
			e.setValorTotal(rs.getInt(3));
			e.setCliente(this.clienteDAO.buscarPorId(rs.getInt(4)));
			// e.setSituacao(rs.getInt(5));
			SQL = "select id_venda, id_produto, qtd from ITEMVENDA where id_venda = ?";
			ps = connector.prepareStatement(SQL);
			ps.setInt(1, e.getId());
			ResultSet rs2 = ps.executeQuery();
			ArrayList<ItemVenda> itens = new ArrayList<>();
			while (rs2.next()) {
				ItemVenda iv = new ItemVenda();
				
				iv.setVenda(e);
				iv.setProduto(this.produtoDAO.buscarPorId(rs.getInt(2)));
				iv.setQtdProduto(rs.getInt(3));
				
				itens.add(iv);		
			}
			e.setItens(itens);
		
			

			vendas.add(e);
		}
		this.conexao.fecharConexao();
		return vendas;
	}

	public Boolean Excluir(int _id) throws SQLException {
		String SQL = "delete from venda where id = ?";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		ps.setInt(1, _id);

		ps.execute();
		this.conexao.fecharConexao();
		return true;
	}

	public Boolean Atualizar(Venda _objeto) throws SQLException {
		String sql = "UPDATE venda SET id_cliente = ?";
        sql += " WHERE id_produto = ?";
        Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(sql);
		ps.setInt(1, _objeto.getCliente().getId());
		ps.setInt(2, _objeto.getId());
		         
        boolean rowUpdated = ps.executeUpdate() > 0;
        conexao.fecharConexao();
        return rowUpdated;     
	}

	public Venda buscarPorId(int _id) throws SQLException {
		ResultSet rs = null;

		String SQL = "select id_venda, data_venda, total_venda, id_cliente, qtd_venda from venda where id_venda = ?";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);
		ps.setInt(1, _id);

		rs = ps.executeQuery();

		Venda e = null;
		if (rs.next()) {

			e = new Venda();
			e.setId(rs.getInt(1));
			e.setDataVenda(rs.getDate(2));
			e.setValorTotal(rs.getInt(3));
			e.setCliente(this.clienteDAO.buscarPorId(rs.getInt(4)));
			// e.setSituacao(rs.getInt(5));
			SQL = "select id_venda, id_produto, qtd from ITEMVENDA where id_venda = ?";
			ps = connector.prepareStatement(SQL);
			ps.setInt(1, e.getId());
			ResultSet rs2 = ps.executeQuery();
			ArrayList<ItemVenda> itens = new ArrayList<>();
			while (rs2.next()) {
				ItemVenda iv = new ItemVenda();
				
				iv.setVenda(e);
				iv.setProduto(this.produtoDAO.buscarPorId(rs.getInt(2)));
				iv.setQtdProduto(rs.getInt(3));
				
				itens.add(iv);		
			}
			e.setItens(itens);
		}
		this.conexao.fecharConexao();
		return e;
	}

}
