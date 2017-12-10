package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.Fila;

public class FilaDAO extends GenericDAO<Fila>{

	public FilaDAO(){
		super(Fila.class);
	}
	/**
	 * Recebe o id da empresa e lista todas as filas.
	 * @param id
	 * @return
	 */
	public List<Fila> listarFilasPorEmpresa(Long id) {
		return em.createQuery("select fila from Fila as fila "
				+ "where fila.empresa.id = :id", Fila.class)
				.setParameter("id", id)
				.getResultList();
	}

}