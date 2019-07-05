package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Cliente;
import dao.ClienteDAO;

@ManagedBean
public class ClienteListagemBean {

	private String nome;

	private List<Cliente> clientes = new ArrayList<>();
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	private boolean pesquisado = false;
	private boolean falha = false;

	public boolean isPesquisado() {
		return pesquisado;
	}

	public void setPesquisado(boolean pesquisado) {
		this.pesquisado = pesquisado;
	}

	private Cliente cliente = new Cliente();

	public ClienteListagemBean() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void listarClientes() throws SQLException {
		System.out.println("Buscando clientes!");

		this.clientes = this.clienteDAO.listarTodos();		
		
		this.pesquisado = true;

		if (this.clientes == null || this.clientes.isEmpty() || this.nome.equals("Gabriel")) {
			this.falha = true;
			this.pesquisado = false;
		}
		
	}

	public boolean isFalha() {
		return falha;
	}

	public void setFalha(boolean falha) {
		this.falha = falha;
	}

}
