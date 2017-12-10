package br.ufpi.lost.dao;

import java.util.List;

import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Usuario;

public class EmpresaDAO extends GenericDAO<Empresa>{
	
	public EmpresaDAO(){
		super(Empresa.class);
	}
	/**
	 * Recebe o id da empresa e lista os funcionarios.
	 * @param id
	 * @return
	 */
	public List<Usuario> listarFuncionariosPorEmpresa(Long id) {
		return em.createQuery("select usuario from Usuario as usuario "
				+ "where usuario.empresa.id = :id", Usuario.class)
				.setParameter("id", id)
				.getResultList();
		
	}

}
