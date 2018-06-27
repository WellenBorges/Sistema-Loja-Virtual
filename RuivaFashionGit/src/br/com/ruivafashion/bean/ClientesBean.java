package br.com.ruivafashion.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.ruivafashion.DAO.ClientesDAO;
import br.com.ruivafashion.domain.Agenda;
import br.com.ruivafashion.domain.Clientes;
import br.com.ruivafashion.util.JSFUtil;


@ManagedBean(name = "MBClientes")
@ViewScoped
public class ClientesBean {
	private Clientes clientes;
	private ArrayList<Clientes> itens;
	private ArrayList<Clientes> itensFiltrados;
	private ArrayList<Agenda> agendas;
	
	
	

	
	
	public ArrayList<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(ArrayList<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Clientes> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Clientes> itens) {
		this.itens = itens;
	}

	public ArrayList<Clientes> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Clientes> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ClientesDAO cdao = new ClientesDAO();
			itens = cdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		clientes = new Clientes();
	}

	public void novo() {

		try {
			ClientesDAO cdao = new ClientesDAO();
			cdao.salvar(clientes);

			itens = cdao.listar();
			JSFUtil.adicionarMensagemSucesso("Cadastro salvo com sucesso! ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ClientesDAO cdao = new ClientesDAO();
			cdao.excluir(clientes);

			itens = cdao.listar();

			JSFUtil.adicionarMensagemSucesso("Cadastro excluído com sucesso! ");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um contato que esteja associado! ");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ClientesDAO cdao = new ClientesDAO();
			cdao.alterar(clientes);

			itens = cdao.listar();

			JSFUtil.adicionarMensagemSucesso("Editado com sucesso! ");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
}
