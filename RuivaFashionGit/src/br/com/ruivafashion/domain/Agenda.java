package br.com.ruivafashion.domain;

public class Agenda {
	private Integer idagenda;
	private String data;
	private String horario;
	private String endereco;
	private Clientes clientes;
	
	
	
	public Clientes getClientes() {
		return clientes;
	}
	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}
	public Integer getIdagenda() {
		return idagenda;
	}
	public void setIdagenda(Integer idagenda) {
		this.idagenda = idagenda;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	

}
