package dao;


import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface ICliente {

	boolean inserir(Cliente _objeto) throws SQLException;
	
	List<Cliente> listarTodos() throws SQLException;
	
	boolean excluir(int _id) throws SQLException;
	
	boolean atualizar(Cliente _objeto) throws SQLException;
	
	Cliente buscarPorId(int _id) throws SQLException;
}
