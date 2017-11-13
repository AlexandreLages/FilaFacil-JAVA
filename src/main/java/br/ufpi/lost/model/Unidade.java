package br.ufpi.lost.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Unidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@OneToMany(mappedBy="unidade", targetEntity=PontoDeAtendimento.class, fetch=FetchType.LAZY)
	private List<PontoDeAtendimento> pontosDeAtendimento = new ArrayList<>();
	
	private String nome;
	private String descricao;
	private String endereco;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<PontoDeAtendimento> getPontosDeAtendimento() {
		return pontosDeAtendimento;
	}

	public void setPontosDeAtendimento(List<PontoDeAtendimento> pontosDeAtendimento) {
		this.pontosDeAtendimento = pontosDeAtendimento;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}	
}