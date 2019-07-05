package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	private Connection conexao;

	public Connection fazerConexao() throws SQLException {
		if (this.conexao == null || this.conexao.isClosed()) {
			try {

				Class.forName("com.mysql.jdbc.Driver");	
				
				this.conexao = DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/webcamadas","root","1234");  
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.conexao;
	}

	public void fecharConexao() {

		try {

			this.conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
