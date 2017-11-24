package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.PontoDeAtendimento;

public class PontoDeAtendimentoDAO extends GenericDAO<PontoDeAtendimento> {

	public PontoDeAtendimentoDAO() {
		super(PontoDeAtendimento.class);
	}

	public List<PontoDeAtendimento> listarPorUnidade(long idUnidade) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.unidade.id = :idUnidade", PontoDeAtendimento.class)
				.setParameter("idUnidade", idUnidade)
				.getResultList();
		
	}

	public List<PontoDeAtendimento> listarPorEmpresa(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
	}

	public List<PontoDeAtendimento> listarPontosAssociados(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id and ponto.unidade != null", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
		
	}

	public List<PontoDeAtendimento> listarPontosDesassociados(Long id) {
		return em.createQuery("select ponto from PontoDeAtendimento as ponto "
				+ "where ponto.empresa.id = :id and ponto.unidade = null", PontoDeAtendimento.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	
}