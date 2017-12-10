package br.ufpi.lost.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.dao.PainelDAO;
import br.ufpi.lost.dao.PontoDeAtendimentoDAO;
import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Painel;
import br.ufpi.lost.model.PontoDeAtendimento;
import br.ufpi.lost.model.UsuarioLogado;

@Controller
public class PainelController {
	
	@Inject private UsuarioLogado usuarioLogado;
	@Inject private Result result;
	@Inject private PainelDAO dao;
	@Inject private PontoDeAtendimentoDAO pontoDeAtendimentoDAO;

	@Permission
	@Get("/painel/telaInicial")
	/**
	 * Carrega tela do painel de atendimento
	 */
	public void telaInicial() {
		Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
		List<Painel> paineis = dao.listarPaineisPorEmpresa(empresa.getId());
		result.include("paineis", paineis);
	}
	
	@Permission
	@Get
	public void adicionar() {}
	
	@Permission
	@Post("/painel/adicionar")
	/**
	 * Adicionar painel a empresa. O metodo recebe o painel como parametro.
	 * @param fila
	 */
	public void adicionar(Painel painel) {
		
		painel.setEmpresa(usuarioLogado.getUsuario().getEmpresa());

		dao.save(painel);
		
		result.include("mensagem", "Painel adicionado com sucesso");
		result.redirectTo(this).telaInicial();
	}
	
	@Permission
	@Get
	/**
	 * Metodo lista as filas por empresas.
	 */
	public void associacoes() {
		List<Painel> paineis = dao.listarPaineisPorEmpresa(usuarioLogado.getUsuario().getEmpresa().getId());
		
		result.include("paineis", paineis);
	}
	
	@Permission
	@Get
	/**
	 * Carrega as filas e os pontos de atendimento
	 */
	public void associar() {
		List<Painel> paineis = dao.listarPaineisPorEmpresa(usuarioLogado.getUsuario().getEmpresa().getId());
		List<PontoDeAtendimento> listarPorEmpresa = pontoDeAtendimentoDAO.listarPorEmpresa(usuarioLogado.getUsuario().getEmpresa().getId());
		
		result.include("pontos", listarPorEmpresa);
		result.include("paineis", paineis);
	}
	
	@Permission
	@Post
	/**
	 * Metodo associa a uma fila a varios pontos de atendimento. O metodo recebe a fila a ser adicionada e a lista de pontos de atendimento
	 * @param idFila
	 * @param pontoDeAtendimentos
	 */
	public void associar(long idPainel, List<Long> pontoDeAtendimentos) {
		Painel findById = dao.findById(idPainel);
		
		for (Long ponto : pontoDeAtendimentos) {
			PontoDeAtendimento pontoDeAtendimento = pontoDeAtendimentoDAO.findById(ponto);
			pontoDeAtendimento.getPaineis().add(findById);
			
			pontoDeAtendimentoDAO.save(pontoDeAtendimento);
		}	
		result.redirectTo(this).associacoes();
	}
}