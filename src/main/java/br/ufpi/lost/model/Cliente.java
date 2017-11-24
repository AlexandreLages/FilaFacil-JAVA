package br.ufpi.lost.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufpi.lost.dao.PersistenceEntity;

@Entity
public class Cliente implements PersistenceEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_fila")
	private Fila fila;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioDeEntrada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioDeAtendimento;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getHorarioDeEntrada() {
		return horarioDeEntrada;
	}

	public void setHorarioDeEntrada(Date horarioDeEntrada) {
		this.horarioDeEntrada = horarioDeEntrada;
	}

	public Date getHorarioDeAtendimento() {
		return horarioDeAtendimento;
	}

	public void setHorarioDeAtendimento(Date horarioDeAtendimento) {
		this.horarioDeAtendimento = horarioDeAtendimento;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}	
}