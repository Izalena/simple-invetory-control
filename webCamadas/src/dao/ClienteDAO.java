package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import dao.FabricaConexao;

public class ClienteDAO implements ICliente {

	FabricaConexao conexao;

	public ClienteDAO() {
		this.conexao = new FabricaConexao();
	}

	public boolean inserir(Cliente _objeto) throws SQLException {

		String SQL = "insert into cliente ( nome_cliente, email_cliente, endereco_cliente, cpf_cliente) values (?, ?, ?, ?)";
		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		ps.setString(1, _objeto.getNome());
		ps.setString(2, _objeto.getEmail());
		ps.setString(3, _objeto.getEndereco());
		ps.setInt(4, _objeto.getCpf());

		boolean inseridoComSucesso = ps.execute();

		this.conexao.fecharConexao();

		return inseridoComSucesso;
	}

	public boolean atualizar(Cliente _objeto) throws SQLException {
		String sql = "UPDATE cliente SET nome_cliente = ?";
		sql += " WHERE idCliente = ?";
		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(sql);
		ps.setString(1, _objeto.getNome());
		ps.setInt(2, _objeto.getId());

		boolean rowUpdated = ps.executeUpdate() > 0;
		conexao.fecharConexao();
		return rowUpdated;
	}

	public boolean excluir(int _id) throws SQLException {

		String SQL = "delete from cliente where id_cliente = ?";
		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);

		ps.setInt(1, _id);

		ps.execute();
		this.conexao.fecharConexao();
		return true;
	}

	public Cliente buscarPorId(int _id) throws SQLException {
		Cliente p = null;

		ResultSet rs = null;

		String SQL = "select id_cliente, nome_cliente, email_cliente, endereco_cliente, cpf_cliente from cliente where id_cliente = ?";

		
		
		if (_id > 0) {
			Connection connector = this.conexao.fazerConexao();
			PreparedStatement ps = connector.prepareStatement(SQL);
			ps.setInt(1, _id);

			rs = ps.executeQuery();

			if (rs.next()) {

				p = new Cliente();

				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setEndereco(rs.getString(4));
				p.setCpf(rs.getInt(5));
			}

		}
		this.conexao.fecharConexao();

		return p;
	}

	public List<Cliente> listarTodos() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;

		String SQL = "select id_cliente, nome_cliente, email_cliente, cpf_cliente, endereco_cliente from cliente";

		Connection connector = this.conexao.fazerConexao();
		PreparedStatement ps = connector.prepareStatement(SQL);
		rs = ps.executeQuery();

		while (rs.next()) {

			Cliente p = new Cliente();

			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setEmail(rs.getString(3));
			p.setCpf(rs.getInt(4));
			p.setEndereco(rs.getString(5));

			clientes.add(p);
		}
		this.conexao.fecharConexao();
		return clientes;
	}

}
