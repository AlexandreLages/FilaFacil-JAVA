package br.ufpi.lost.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufpi.lost.dao.PersistenceEntity;

@Entity
public class Fila implements PersistenceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToMany(mappedBy="filas")
	private List<PontoDeAtendimento> pontosDeAtendimento = new ArrayList<PontoDeAtendimento>();
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@OneToMany(mappedBy="fila", targetEntity=Cliente.class,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Cliente> clientes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PontoDeAtendimento> getPontosDeAtendimento() {
		return pontosDeAtendimento;
	}

	public void setPontosDeAtendimento(List<PontoDeAtendimento> pontosDeAtendimento) {
		this.pontosDeAtendimento = pontosDeAtendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}