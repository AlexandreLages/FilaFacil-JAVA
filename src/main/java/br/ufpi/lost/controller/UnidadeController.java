package br.ufpi.lost.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.dao.UnidadeDAO;
import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Unidade;
import br.ufpi.lost.model.UsuarioLogado;

@Controller
public class UnidadeController {
	
	@Inject private UnidadeDAO unidadeDAO;
	@Inject private UsuarioLogado usuarioLogado;
	@Inject private Result result;
	
	@Permission
	@Get
	/**
	 * Mostra as unidades.
	 */
	public void telaInicial() {
			Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
			List<Unidade> unidades = unidadeDAO.listarPorEmpresa(empresa.getId());
			result.include("unidades", unidades);
	}
	
	@Permission
	@Get("/unidade/adicionar")
	public void adicionar() {}
	
	@Permission
	@Post
	/**
	 * Realiza operacao de adicao de unidade e volta para a tela inicial. Recebe a unidade como parametro.
	 * @param unidade
	 */
	public void adicionar(Unidade unidade) {
		if(usuarioLogado.isLogado()) {
			Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
			
			unidade.setEmpresa(empresa);
			unidadeDAO.save(unidade);
			
			result.include("mensagem", "Unidade adicionada com sucesso");
			result.redirectTo(UnidadeController.class).telaInicial();
		}
	}
}
