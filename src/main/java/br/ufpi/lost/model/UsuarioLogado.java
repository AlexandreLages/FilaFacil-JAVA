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
	
	/**
	 * Varifica se usuario esta logado
	 * @return
	 */
	public boolean isLogado(){
		return usuario != null;
	}
	/**
	 * Realiza o logout do usuario
	 */
	public void logout(){
		this.usuario = null;
	}
	/**
	 * Retorna o nome do usuario.
	 * @return
	 */
	public String getNome(){
		return usuario.getNome();
	}
	/**
	 * Retorna email do usuario
	 * @return
	 */
	public String getEmail(){
		return usuario.getEmail();
	}
	/**
	 * Retorna objeto usuario.
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
	}
}