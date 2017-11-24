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
	@Get("/ponto/listar")
	public void telaInicial() {
	
			List<PontoDeAtendimento> pontosDeAtendimento = pontoDeAtendimentoDAO.listarPorEmpresa(usuarioLogado.getUsuario().getEmpresa().getId());
			
			result.include("pontosDeAtendimento", pontosDeAtendimento);
	}
	
	@Permission
	@Get("/ponto/adicionar")
	public void adicionar() {
	}
	
	@Permission
	@Post("/ponto/adicionar")
	public void adicionar(PontoDeAtendimento ponto) {
		ponto.setEmpresa(usuarioLogado.getUsuario().getEmpresa());
		
		pontoDeAtendimentoDAO.save(ponto);
		
		result.include("mensagem", "Ponto de atendimento cadastrado com sucesso");
		result.redirectTo(PontoDeAtendimentoController.class).telaInicial();
	}
	
	
	@Permission
	@Get
	public void associacoes() {
		List<PontoDeAtendimento> listarPontosAssociados = pontoDeAtendimentoDAO.listarPontosAssociados(usuarioLogado.getUsuario().getEmpresa().getId());
		
		result.include("pontos", listarPontosAssociados);
	}
	
	@Permission
	@Get("/ponto/associar")
	public void associar() {
		List<Unidade> listarUnidades = unidadeDAO.listarPorEmpresa(usuarioLogado.getUsuario().getEmpresa().getId());
		List<PontoDeAtendimento> listarPontosDesassociados = pontoDeAtendimentoDAO.listarPontosDesassociados(usuarioLogado.getUsuario().getEmpresa().getId());
		
		result.include("unidades", listarUnidades);
		result.include("pontos", listarPontosDesassociados);
	}
	
	@Permission
	@Post("/ponto/associar")
	public void associar(long idUnidade, long idPonto) {
		Unidade unidade = unidadeDAO.findById(idUnidade);
		PontoDeAtendimento ponto = pontoDeAtendimentoDAO.findById(idPonto);
		
		ponto.setUnidade(unidade);
		
		pontoDeAtendimentoDAO.save(ponto);
		
		result.include("mensagem", "Ponto atualizado com sucesso");
		result.redirectTo(PontoDeAtendimentoController.class).associacoes();
	}
}