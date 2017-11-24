package br.ufpi.lost.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Public;
import br.ufpi.lost.dao.UsuarioDAO;
import br.ufpi.lost.model.Usuario;
import br.ufpi.lost.model.UsuarioLogado;

@Controller
public class LoginController {

	@Inject private UsuarioDAO usuarioDAO;
	@Inject private UsuarioLogado usuarioLogado;
	@Inject private Result result;	
	
	@Public
	@Get("/usuario/login")
	public void login() {}
	
	@Public
	@Post
	public void login(String login, String password) {
		
		Usuario usuario = usuarioDAO.buscarPorLoginESenha(login, password);
		
		if(usuario != null) {
			usuarioLogado.login(usuario);
			if(usuarioLogado.isLogado()) {
				result.redirectTo(UnidadeController.class).telaInicial();
			}
		}
	}
	
	@Public
	@Path("/usuario/logout")
	public void logout() {
		usuarioLogado.logout();
		result.redirectTo(LoginController.class).login();
	}
}