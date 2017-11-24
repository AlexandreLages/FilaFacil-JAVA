package br.ufpi.lost.dao;

import javax.persistence.NoResultException;

import br.ufpi.lost.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{

	
	public UsuarioDAO(){
		super(Usuario.class);
	}
	
	
	public Usuario buscarPorLoginESenha(String login, String senha) {
		try{
			return em.createQuery("select usuario from Usuario as usuario where usuario.login = :login "
					+ "and usuario.password = :senha", Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		} catch (NoResultException e1) {
			return null;
		}
	}

}
