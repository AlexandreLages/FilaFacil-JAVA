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
	
	
	private boolean prioritario = false;
	/**
	 * Retorna o id da fila.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Seta o id da fila..
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Retorna os pontos de atendimento da fila.
	 * @return
	 */
	public List<PontoDeAtendimento> getPontosDeAtendimento() {
		return pontosDeAtendimento;
	}
	/**
	 * Seta os pontos de atendimento da fila.
	 * @param pontosDeAtendimento
	 */
	public void setPontosDeAtendimento(List<PontoDeAtendimento> pontosDeAtendimento) {
		this.pontosDeAtendimento = pontosDeAtendimento;
	}
	/**
	 * Retorna a descricao da fila.
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Seta a descricao da fila.
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * Retorna a empresa da fila.
	 * @return
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	/**
	 * Seta a empresa da fila.
	 * @param empresa
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	/**
	 * Retorna os clientes da fila.
	 * @return
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}
	/**
	 * Seta os cliente da fila.
	 * @param clientes
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public boolean isPrioritario() {
		return prioritario;
	}
	public void setPrioritario(boolean prioritario) {
		this.prioritario = prioritario;
	}
	
	@Override
	public String toString() {
		String resp = this.descricao;
		
		if(prioritario) {
			resp = resp + " - Prioritária";
		}
		
		return resp;
	}
}