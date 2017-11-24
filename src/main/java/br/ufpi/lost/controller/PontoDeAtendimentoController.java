package br.ufpi.lost.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.dao.PontoDeAtendimentoDAO;
import br.ufpi.lost.dao.UnidadeDAO;
import br.ufpi.lost.model.PontoDeAtendimento;
import br.ufpi.lost.model.Unidade;
import br.ufpi.lost.model.UsuarioLogado;

@Controller
public class PontoDeAtendimentoController {

	@Inject private UnidadeDAO unidadeDAO;
	@Inject private PontoDeAtendimentoDAO pontoDeAtendimentoDAO;
	@Inject private UsuarioLogado usuarioLogado;
	@Inject private Result result;

	@Permission
	@Get("/ponto/{idUnidade}/listar")
	public void telaInicial(long idUnidade) {
		if(usuarioLogado.isLogado()) {
			List<PontoDeAtendimento> pontosDeAtendimento = pontoDeAtendimentoDAO.listarPorUnidade(idUnidade);
			Unidade unidade = unidadeDAO.findById(idUnidade);
			
			result.include("pontosDeAtendimento", pontosDeAtendimento);
			result.include("idUnidade", idUnidade);
			result.include("unidade", unidade);
		}
	}
	
	@Permission
	@Get("/ponto/{idUnidade}/adicionar")
	public void adicionar(long idUnidade) {
		result.include("idUnidade", idUnidade);
	}
	
	@Permission
	@Post("/ponto/adicionar")
	public void adicionar(PontoDeAtendimento ponto, long idUnidade) {
		Unidade unidade = unidadeDAO.findById(idUnidade);
		ponto.setUnidade(unidade);
		
		pontoDeAtendimentoDAO.save(ponto);
		
		result.include("mensagem", "Ponto de atendimento cadastrado com sucesso");
		result.redirectTo(PontoDeAtendimentoController.class).telaInicial(idUnidade);
	}
}