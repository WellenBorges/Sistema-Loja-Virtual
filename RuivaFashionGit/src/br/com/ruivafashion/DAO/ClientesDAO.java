package br.com.ruivafashion.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ruivafashion.domain.Agenda;
import br.com.ruivafashion.domain.Clientes;
import br.com.ruivafashion.domain.Contatos;
import br.com.ruivafashion.factory.ConexaoBd;

public class ClientesDAO {
	public void salvar(Clientes c) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO clientes ");
		sql.append("(nome, ");
		sql.append("cpf, ");
		sql.append("rg, ");
		sql.append("sexo, ");
		sql.append("email, ");
		sql.append("datanascimento, ");
		sql.append("senha) ");
		sql.append(" VALUES (?,?,?,?,?,?,?) ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement cliente = conexao.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

		cliente.setString(1, c.getNome());
		cliente.setString(2, c.getCpf());
		cliente.setString(3, c.getRg());
		cliente.setString(4, c.getSexo());
		cliente.setString(5, c.getEmail());
		cliente.setString(6, c.getDatanascimento());
		cliente.setString(7, c.getSenha());
		cliente.execute();

		ResultSet rs = cliente.getGeneratedKeys();
		int fk = 0;
		if (rs.next()) {
			fk = (int) rs.getInt(1);
		}

		for (Agenda a : c.getAgendas()) {
			sql = new StringBuilder();
			sql.append("INSERT INTO agenda ");
			sql.append("(horario, ");
			sql.append("clientes_idcliente,");
			sql.append("data )");
			sql.append(" VALUES (?,?,?) ");

			cliente = conexao.prepareStatement(sql.toString());

			cliente.setString(1, a.getHorario());
			cliente.setInt(2, fk);
			cliente.setString(3, a.getData());
			cliente.execute();
		}

		
		
	
			sql = new StringBuilder();
			sql.append("INSERT INTO contatos ");
			sql.append("(cep, ");
			sql.append("endereco, ");
			sql.append("cidade, ");
			sql.append("estado, ");
			sql.append("bairro, ");
			sql.append("pontoreferencia, ");
			sql.append("telefone, ");
			sql.append("telefone2, ");
			sql.append("clientes_idcliente)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
			
			
			PreparedStatement contato = conexao.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			
			Contatos cont =new Contatos();
			contato.setString(1, cont.getCep());
			contato.setString(2, cont.getEndereco());
			contato.setString(3, cont.getCidade());
			contato.setString(4, cont.getEstado());
			contato.setString(5, cont.getBairro());
			contato.setString(6, cont.getPontoreferencia());
			contato.setString(7, cont.getTelefone());
			contato.setString(8, cont.getTelefone2());
			contato.setInt(9, fk);
			contato.execute();
			
			
		
			
		
	}

	public void excluir(Clientes c) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM clientes  ");
		sql.append("WHERE idcliente = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, c.getIdcliente());
		comando.executeUpdate();

	}

	public void alterar(Clientes c) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE clientes  ");
		sql.append("SET nome = ?,cpf = ?,rg = ?,sexo = ?,email = ?,datanascimento = ?");
		sql.append("WHERE idcliente = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, c.getNome());
		comando.setString(2, c.getCpf());
		comando.setString(3, c.getRg());
		comando.setString(4, c.getSexo());
		comando.setString(5, c.getEmail());
		comando.setString(6, c.getDatanascimento());
		comando.setString(7, c.getSenha());
		comando.setInt(8, c.getIdcliente());

		comando.execute();
	}

	public Clientes Pesquisar(Clientes c) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idcliente,nome,cpf ");
		sql.append("FROM clientes ");
		sql.append("WHERE idcliente = ? ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setInt(1, c.getIdcliente());

		ResultSet resultado = comando.executeQuery();
		Clientes retorno = null;

		if (resultado.next()) {
			retorno = new Clientes();
			retorno.setIdcliente(resultado.getInt("idcliente"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setCpf(resultado.getString("cpf"));
		}

		return retorno;

	}

	public ArrayList<Clientes> pesquisarPorNome(Clientes c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idcliente,nome ");
		sql.append("FROM clientes ");
		sql.append("WHERE nome LIKE  ? ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + c.getNome() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Clientes> lista = new ArrayList<Clientes>();

		while (resultado.next()) {
			Clientes item = new Clientes();
			item.setIdcliente(resultado.getInt("idcliente"));
			item.setNome(resultado.getString("nome"));
			lista.add(item);
		}
		return lista;

	}

	public ArrayList<Clientes> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idcliente,nome,cpf,rg,sexo,email,datanascimento ");
		sql.append("FROM clientes ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoBd.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Clientes> lista = new ArrayList<Clientes>();

		while (resultado.next()) {
			Clientes c = new Clientes();
			c.setIdcliente(resultado.getInt("idcliente"));
			c.setNome(resultado.getString("nome"));
			c.setCpf(resultado.getString("cpf"));
			c.setRg(resultado.getString("rg"));
			c.setSexo(resultado.getString("sexo"));
			c.setEmail(resultado.getString("email"));
			c.setDatanascimento(resultado.getString("datanascimento"));

			lista.add(c);
		}
		return lista;
	}
	
	
	

	public static void main(String[] args) {

		Clientes c1 = new Clientes();

		c1.setNome("testes finais");
		c1.setCpf("999.999.999-99");
		c1.setRg("9999.999");
		c1.setSexo("M");
		c1.setEmail("exemplo");
		c1.setDatanascimento("99/99/9999");
		c1.setSenha("1515");

		Agenda ag = new Agenda();
		ag.setData("99/99/9999");
		ag.setHorario("00:00");
		c1.getAgendas().add(ag);

		Agenda ag1 = new Agenda();
		ag1.setData("12/12/2018");
		ag1.setHorario("10:00");
		c1.getAgendas().add(ag1);
		
		Contatos cont1 = new Contatos();
		cont1.setCep("99.999-999");
		cont1.setEndereco("Rua, Quadra");
		cont1.setCidade("Goiás");
		cont1.setEstado("DF");
		cont1.setBairro("Imperio");
		cont1.setPontoreferencia("perto");
		cont1.setTelefone("9999999");
		cont1.setTelefone2("99999999");
		c1.getContatos();
		
		
		
		ClientesDAO cdao = new ClientesDAO();
		

		try {
			cdao.salvar(c1);
			

			System.out.println("Salvo...");
		} catch (SQLException e) {
			System.out.println("erro...");
			e.printStackTrace();
		}
		

		/*
		 * Clientes c1 = new Clientes();
		 * 
		 * c1.setIdclientes(5); c1.setNome("Davi"); c1.setCpf("NT"); c1.setRg("nao");
		 * c1.setSexo("M"); c1.setEmail("wellen.borgesnunes19@hotmail.com");
		 * c1.setDatanascimento("30/07/2012");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * ClientesDAO cdao = new ClientesDAO();
		 * 
		 * try { cdao.excluir(c1); System.out.println("Deletado..."); } catch
		 * (SQLException e) { System.out.println("erro..."); e.printStackTrace();
		 * 
		 * }
		 */

		/*
		 * Clientes c1 = new Clientes(); c1.setIdclientes(2); c1.setNome("Davi");
		 * 
		 * ClientesDAO cdao = new ClientesDAO();
		 * 
		 * try { cdao.alterar(c1); System.out.println("Alterado..."); } catch
		 * (SQLException e) { System.out.println("erro..."); e.printStackTrace();
		 * 
		 * }
		 */
		/*
		 * Clientes c1 = new Clientes();
		 * 
		 * c1.setIdclientes(2); c1.setNome("Davi");
		 * 
		 * Clientes c2 = new Clientes(); c2.setIdclientes(5);
		 * 
		 * ClientesDAO cdao = new ClientesDAO();
		 * 
		 * try { Clientes f3=cdao.Pesquisar(c1); Clientes f4=cdao.Pesquisar(c2);
		 * System.out.println("Resultado: " + f3); System.out.println("Resultado:" +
		 * f4);} catch (SQLException e) { System.out.println("erro...");
		 * e.printStackTrace(); }
		 */

		/*
		 * ClientesDAO cdao = new ClientesDAO();
		 * 
		 * try { ArrayList<Clientes> lista = cdao.listar(); for (Clientes c : lista) {
		 * System.out.println("Resultado  " + c); } }catch( SQLException e) {
		 * System.out.println("erro..."); e.printStackTrace(); }
		 */

	}

	public Clientes getEmail(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}
}
