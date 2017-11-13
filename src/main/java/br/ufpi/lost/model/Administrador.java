package br.ufpi.lost.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_permissao")
public class Administrador extends Permissao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}