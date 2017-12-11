package br.ufpi.lost.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.ufpi.lost.dao.PersistenceEntity;
import br.ufpi.lost.model.enums.TipoPrioridade;

@Entity
public class Empresa implements PersistenceEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cnpj;
	private String descricao;
	
	@OneToMany(mappedBy="empresa", targetEntity=Usuario.class,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@OneToMany(mappedBy="empresa", targetEntity=Unidade.class,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Unidade> unidades = new ArrayList<Unidade>();
	/**
	 * Retorna o id da empresa.
	 */
	
	@Enumerated(EnumType.STRING)
	private TipoPrioridade tipoPrioridade = TipoPrioridade.PRIORITARIO;
	
	public Long getId() {
		return id;
	}
	/**
	 * Seta o id da empresa.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Retorna o nome da empresa.
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Seta o nome da empresa.
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Retorna o Cnpj da empresa.
	 * @return
	 */
	public String getCnpj() {
		return cnpj;
	}
	/** 
	 * Seta o Cnpj da empresa.
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	/**
	 * Retorna as descricao da empresa.
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Seta a descricao da empresa.
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * Retorna os usuarios da empresa.
	 * @return
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	/**
	 * Seta os usuarios da empresa.
	 * @param usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	/**
	 * Retorna as unidades da empresa.
	 * @return
	 */
	public List<Unidade> getUnidades() {
		return unidades;
	}
	/**
	 * Seta as unidades da empresa.
	 * @param unidades
	 */
	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	/**
	 * @return the tipoPrioridade
	 */
	public TipoPrioridade getTipoPrioridade() {
		return tipoPrioridade;
	}
	/**
	 * @param tipoPrioridade the tipoPrioridade to set
	 */
	public void setTipoPrioridade(TipoPrioridade tipoPrioridade) {
		this.tipoPrioridade = tipoPrioridade;
	}
}