package br.com.ruivafashion.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.ruivafashion.domain.Produtos;
import br.com.ruivafashion.factory.ConexaoBd;

public class ProdutosDAO {
	
	public void salvar(Produtos p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(nome, ");
		sql.append("tipo, ");
		sql.append("descricao, ");
		sql.append("precocusto, ");
		sql.append("precovenda, ");
		sql.append("quantidade) ");
		sql.append("VALUES (?,?,?,?,?,?) ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getNome());
		comando.setString(2, p.getTipo());
		comando.setString(3, p.getDescricao());
		comando.setString(4, p.getPrecocusto());
		comando.setString(5, p.getPrecovenda());
		comando.setString(6, p.getQuantidade());
		comando.execute();

	}

	public void excluir(Produtos p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos  ");
		sql.append("WHERE idprodutos = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, p.getIdprodutos());
		comando.execute();

	}

	public void alterar(Produtos p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos  ");
		sql.append("SET nome = ?,tipo = ?,descricao = ?,precocusto = ?,precovenda = ?,quantidade = ?");
		sql.append("WHERE idprodutos = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getNome());
		comando.setString(2, p.getTipo());
		comando.setString(3, p.getDescricao());
		comando.setString(4, p.getPrecocusto());
		comando.setString(5, p.getPrecovenda());
		comando.setString(6, p.getQuantidade());
		comando.setInt(7, p.getIdprodutos());
		comando.execute();
	}

	public Produtos Pesquisar(Produtos p) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idprodutos, nome, tipo, descricao, precocusto, precovenda, quantidade");
		sql.append("FROM produtos ");
		sql.append("WHERE idprodutos = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, p.getIdprodutos());
	
		ResultSet resultado = comando.executeQuery();
		Produtos retorno = null;

		if (resultado.next()) {
			retorno = new Produtos();
			retorno.setIdprodutos(resultado.getInt("idprodutos"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setTipo(resultado.getString("tipo"));
			retorno.setDescricao(resultado.getString("descricao"));
			retorno.setPrecocusto(resultado.getString("precocusto"));
			retorno.setPrecovenda(resultado.getString("precovenda"));
			retorno.setQuantidade(resultado.getString("quantidade"));
			
		}

		return retorno;

	}
	
	public ArrayList<Produtos> pesquisarPorNome(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idprodutos ,nome " );
		sql.append("FROM produtos " );
		sql.append("WHERE nome LIKE  ? " );
		sql.append("ORDER BY nome ASC ");
		
		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + p.getNome() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Produtos item = new Produtos();
			item.setIdprodutos(resultado.getInt("idprodutos"));
			item.setNome(resultado.getString("nome"));	
			lista.add(item);
		}
		return lista;
		
		
		
	}

	public ArrayList<Produtos> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idprodutos,nome,tipo, descricao, precocusto, precovenda, quantidade  ");
		sql.append("FROM produtos ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Produtos p = new Produtos();
			p.setIdprodutos(resultado.getInt("idprodutos"));
			p.setNome(resultado.getString("nome"));
			p.setTipo(resultado.getString("tipo"));	
			p.setDescricao(resultado.getString("descricao"));
			p.setPrecocusto(resultado.getString("precocusto"));
			p.setPrecovenda(resultado.getString("precovenda"));
			p.setQuantidade(resultado.getString("quantidade"));	
			lista.add(p);
		}
		return lista;
		
	}

	public static void main(String[] args) {

		
		Produtos p1 = new Produtos();

		p1.setNome("Short verdana");
		p1.setTipo("Shorts");
		p1.setDescricao(" 40");
		p1.setPrecocusto("30,00");
		p1.setPrecovenda("100,00");
		p1.setQuantidade("20");
		
		ProdutosDAO pdao = new ProdutosDAO();

		try {
			pdao.salvar(p1);
			
			System.out.println("Salvo...");
		} catch (SQLException e) {
			System.out.println("erro...");
			e.printStackTrace();
		}
		  
		/*Produtos p1 = new Produtos();
		
		p1.setIdprodutos(5);
		p1.setNome("Davi");
		p1.setTipo("NT");
		p1.setDescricao("nao");
		p1.setPrecocusto("M");
		p1.setPrecovenda("wellen.borgesnunes19@hotmail.com");
		p1.setQuantidade("30/07/2012");
		


		

		ProdutosDAO cdao = new ProdutosDAO();

		try {
			cdao.excluir(p1);
			System.out.println("Deletado...");
		} catch (SQLException e) {
			System.out.println("erro...");
			e.printStackTrace();

		}*/

		/*Produtos p1 = new Produtos();
		p1.setIdprodutos(2);
		p1.setNome("Saia de couro");
		p1.setTipo("Saia");	
		p1.setDescricao("couro fulero");
		p1.setPrecocusto("15,00");
		p1.setPrecovenda("50,00");
		p1.setQuantidade("5");

		ProdutosDAO pdao = new ProdutosDAO();

		try {
			pdao.alterar(p1);
			System.out.println("Alterado...");
		} catch (SQLException e) {
			System.out.println("erro...");
			e.printStackTrace();

		}*/
	
		/*Produtos p1 = new Produtos();
		
		p1.setIdprodutos(2);
		p1.setNome("Body");
		
		Produtos p2 = new Produtos();
		p2.setIdprodutos(3);
		 
		 ProdutosDAO pdao = new ProdutosDAO();
		 
		 try { 
			 Produtos f3 = pdao.Pesquisar(p1);
			 Produtos f4 = pdao.Pesquisar(p2);
			 System.out.println("Resultado: " + f3);
			 System.out.println("Resultado:" + f4);} 
		 catch
		 (SQLException e)
		 { System.out.println("erro..."); 
		 e.printStackTrace();
		 }*/
		 
 /*ProdutosDAO pdao = new ProdutosDAO();
		 
		 try { 
			 ArrayList<Produtos>lista = pdao.listar();
			 for(Produtos p : lista) {
			 System.out.println("Resultado  " + p);
			 }
		 }
		 catch
		 (SQLException e)
		 { System.out.println("erro..."); 
		 e.printStackTrace();*/
		

		/*Produtos p1 = new Produtos();
		
		p1.setNome("bo");
		
		ProdutosDAO pdao = new ProdutosDAO();
		 try { 
			 ArrayList<Produtos>lista =pdao.pesquisarPorNome(p1);
			 for(Produtos p : lista) {
			 System.out.println("Resultado  " + p);
			 }
		 }
		 catch
		 (SQLException e)
		 { System.out.println("erro..."); 
		 e.printStackTrace();*/
		
		
		/*Clientes c2 = new Clientes();
		c2.setIdclientes(5);*/
		 
		 
	//}
	}
	}





