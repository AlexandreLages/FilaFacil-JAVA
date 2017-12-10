package br.ufpi.lost.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.annotation.Public;
import br.ufpi.lost.dao.EmpresaDAO;
import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Usuario;

/*
 * 
 */

@Controller
public class EmpresaController {

	@Inject private Result result;
	@Inject private EmpresaDAO empresaDAO;
    	
	@Permission
	@Get("/empresa/adicionar")
	public void adicionar() {}
	
	
	@Permission
	@Post
	/**
	 *Este metodo adiciona um usuario a uma empresa. A empresa e o usuario devem ser passados como parametros. 
	 * @param empresa
	 * @param usuario
	 */
	public void adicionar(Empresa empresa, Usuario usuario) {
		
		empresa.getUsuarios().add(usuario);
		
		usuario.setEmpresa(empresa);
		
		empresaDAO.save(empresa);
		
		result.include("mensagem", "Empresa adicionada com sucesso");
		result.redirectTo(LoginController.class).login();
	}
	@Permission
	@Get
	/**
	 * Este metodo direciona para a tela inicial da empresa
	 */
	public void telaInicial() {}
}