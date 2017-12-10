package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.Painel;

public class PainelDAO extends GenericDAO<Painel>{

	public PainelDAO(){
		super(Painel.class);
	}
	
	public List<Painel> listarPaineisPorEmpresa(Long id) {
		return em.createQuery("select painel from Painel as painel "
				+ "where painel.empresa.id = :id", Painel.class)
				.setParameter("id", id)
				.getResultList();
	}
}