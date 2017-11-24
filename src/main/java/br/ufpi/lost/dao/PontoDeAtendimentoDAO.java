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
	
	
}