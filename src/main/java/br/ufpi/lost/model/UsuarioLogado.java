package br.ufpi.lost.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named 
@SessionScoped
public class UsuarioLogado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public void login(final Usuario usuario){
		this.usuario = usuario;
	}
	
	public String getNome(){
		return usuario.getNome();
	}
	
	public boolean isLogado(){
		return usuario != null;
	}
	
	public void logout(){
		this.usuario = null;
	}
	
	public String getEmail(){
		return usuario.getEmail();
	}

	public Usuario getUsuario() {
		return usuario;
	}
}