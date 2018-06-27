package br.com.ruivafashion.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBd {

	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String URL = "jdbc:mysql://127.0.01:3307/ruivafashion";

	public static Connection conectar() throws SQLException {
		//Referência ao Driver mysql para versões antigas do Java.
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

public static void main(String[] args) {
	try {
	Connection conexao = ConexaoBd.conectar();
	System.out.println("Conectado...");
	}
	catch(SQLException ex) {
		System.out.println("Erro...");
		ex.printStackTrace();
	}
}
}
