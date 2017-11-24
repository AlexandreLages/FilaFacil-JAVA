package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.Unidade;

public class UnidadeDAO extends GenericDAO<Unidade>{

	public UnidadeDAO() {
		super(Unidade.class);
	}

	public List<Unidade> listarPorEmpresa(Long id) {
		return em.createQuery("select unidade from Unidade as unidade "
				+ "where unidade.empresa.id =:idEmpresa", Unidade.class)
				.setParameter("idEmpresa", id)
				.getResultList();
	}

}