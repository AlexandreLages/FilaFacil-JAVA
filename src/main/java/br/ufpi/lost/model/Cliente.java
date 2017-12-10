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
	/**
	 * Retorna cpf do cliente.
	 * @return
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * Seta o cpf do cliente.
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * Retorna o nome do lciente.
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Seta o nome do cliente.
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Retorna o horario que o cliente entrou na fila.
	 * @return
	 */
	public Date getHorarioDeEntrada() {
		return horarioDeEntrada;
	}
	/**
	 * Sera o horario que o cliente entrou na fila.
	 * @param horarioDeEntrada
	 */
	public void setHorarioDeEntrada(Date horarioDeEntrada) {
		this.horarioDeEntrada = horarioDeEntrada;
	}
	/**
	 * Retorna o horario que o cliente foi atendido.
	 * @return
	 */
	public Date getHorarioDeAtendimento() {
		return horarioDeAtendimento;
	}
	/**
	 * Seta o horario que o cliente foi atendido.
	 * @param horarioDeAtendimento
	 */
	public void setHorarioDeAtendimento(Date horarioDeAtendimento) {
		this.horarioDeAtendimento = horarioDeAtendimento;
	}

	@Override
	/**
	 * Retorna o id do cliente.
	 */
	public Long getId() {
		return this.id;
	}

	@Override
	/**
	 * Seta o id do cliente.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Retorna a fila que o cliente se encontra.
	 * @return
	 */
	public Fila getFila() {
		return fila;
	}
	/**
	 * Seta a fila que o cliente se encontra.
	 * @param fila
	 */
	public void setFila(Fila fila) {
		this.fila = fila;
	}	
}