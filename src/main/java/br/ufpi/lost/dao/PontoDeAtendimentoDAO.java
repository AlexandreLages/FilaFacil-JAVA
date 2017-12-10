package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.PontoDeAtendimento;

public class PontoDeAtendimentoDAO extends GenericDAO<PontoDeAtendimento> {

	public PontoDeAtendimentoDAO() {
		super(PontoDeAtendimento.class);
	}
	/**
	 * Recebe a unidade e retorna uma lista dos pontos de atendimento.
	 * @param idUnidade
	 * @return
	 */
	public List<PontoDeAtendimento> listarPorUnidade(long idUnidade) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.unidade.id = :idUnidade", PontoDeAtendimento.class)
				.setParameter("idUnidade", idUnidade)
				.getResultList();
		
	}
	/**
	 * Recebe  o id da empresa e retorna uma lista dos pontos de atendimento.
	 * @param id
	 * @return
	 */
	public List<PontoDeAtendimento> listarPorEmpresa(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
	}
	/**
	 * Lista os pontos de atendimentos associados a uma empresa. Recebe o id da empresa como parametro
	 * @param id
	 * @return
	 */
	public List<PontoDeAtendimento> listarPontosAssociados(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id and ponto.unidade != null", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
		
	}
	/**
	 * Lista os pontos de atendimentos associados a uma empresa. Recebe o id da empresa como parametro
	 * @param id
	 * @return
	 */
	public List<PontoDeAtendimento> listarPontosDesassociados(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id and ponto.unidade = null", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	
}