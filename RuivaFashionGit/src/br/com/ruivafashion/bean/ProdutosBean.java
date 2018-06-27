package br.com.ruivafashion.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ruivafashion.DAO.ProdutosDAO;

import br.com.ruivafashion.domain.Produtos;
import br.com.ruivafashion.util.JSFUtil;

@ManagedBean (name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	
	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;

	public Produtos getProdutos() {
		return produtos;
	}
	
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
@PostConstruct
public void prepararPesquisa() {

		try {
			ProdutosDAO pdao = new ProdutosDAO();
			itens = pdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		produtos = new Produtos();
	}

	public void novo() {

		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.salvar(produtos);

			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Cadastro salvo com sucesso! ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.excluir(produtos);

			itens = pdao.listar();

			JSFUtil.adicionarMensagemSucesso("Cadastro excluído com sucesso! ");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um contato que esteja associado! ");
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.alterar(produtos);

			itens = pdao.listar();

			JSFUtil.adicionarMensagemSucesso("Editado com sucesso! ");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
