package controller;

import org.primefaces.model.chart.*;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Cliente;
import dao.ClienteDAO;

@javax.annotation.ManagedBean
public class ClienteCadastroBean {
	
	private PieChartModel pieModel;
	private int id;
	private int cpf;
	private String nome;
	private String endereco;
	private String email;
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	private boolean sucesso = false;

	public PieChartModel getPieModel(){
		return pieModel;
	}
	
	public PieChartModel setPieModel(PieChartModel pieModel){
		return this.pieModel = pieModel;
	}
	
	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	private Cliente cliente = new Cliente();

	public ClienteCadastroBean() {

	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void gravar() throws Exception {
		System.out.println("Salvando cliente: " + this.cliente.getNome());

		if (this.cliente == null || (this.cliente.getNome() == null || this.cliente.getEmail() == null)) {
			throw new RuntimeException("Cliente deve existir!");
		}
		try {
			Cliente clienteTest = this.clienteDAO.buscarPorId(this.getId());
			if (clienteTest != null) {
				throw new RuntimeException("Cliente com esse id já existe.");
			}

			this.clienteDAO.inserir(this.cliente);
			System.out.println("Cliente salvo" + this.cliente.getNome() + "com sucesso!");
			this.sucesso = true;

		} catch (Exception e) {
			throw e;
		}
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void graficar(List<Cliente> lista) {
		pieModel = new PieChartModel();
		
		for(Cliente cli : lista){
			pieModel.set(cli.getEndereco(), cli.getId());
		}
		
		pieModel.setTitle("Região dos Clientes");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
	}

}
