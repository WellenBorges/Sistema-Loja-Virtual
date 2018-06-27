package br.com.ruivafashion.bean;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.ruivafashion.factory.ConexaoBd;

@ManagedBean(name = "MBLogin")
@RequestScoped
public class LoginBean {
	
	 private String email;
		private String senha;
	
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public void login() throws SQLException {
			Connection conexao = ConexaoBd.conectar();
			String sql = "SELECT email, senha FROM clientes WHERE email = ? AND senha = ? ";
					
			PreparedStatement conectar = conexao.prepareStatement(sql);
			
			conectar.setString(1, email);
			conectar.setString(2, senha);
			
			ResultSet rs = conectar.executeQuery();
			FacesContext context = FacesContext.getCurrentInstance();
		
			if(rs.next()){
		             context.getExternalContext().getSessionMap().put("user", email);
		             try {
		 context.getExternalContext().redirect("principal.xhtml");
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		         }
		         else  {
		                    //Send an error message on Login Failure 
		             context.addMessage(null, new FacesMessage("Dados incorretos. Verifique  usuário e senha."));
		 
		         } 
		     }
		 
		     public void logout() {
		     	FacesContext context = FacesContext.getCurrentInstance();
		     	context.getExternalContext().invalidateSession();
		         try {
		 context.getExternalContext().redirect("login.xhtml");
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		     }
	 
	}
	

