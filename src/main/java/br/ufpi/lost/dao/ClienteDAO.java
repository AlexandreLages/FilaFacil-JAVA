package br.ufpi.lost.dao;

import br.ufpi.lost.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}
}