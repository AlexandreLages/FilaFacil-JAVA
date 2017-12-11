package br.ufpi.lost.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.dao.ClienteDAO;
import br.ufpi.lost.dao.FilaDAO;
import br.ufpi.lost.dao.PontoDeAtendimentoDAO;
import br.ufpi.lost.model.Cliente;
import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Fila;
import br.ufpi.lost.model.PontoDeAtendimento;
import br.ufpi.lost.model.UsuarioLogado;
import br.ufpi.lost.model.enums.TipoPrioridade;
import br.ufpi.lost.util.OrdenacaoGenerica;
import br.ufpi.lost.util.SortType;

@Controller
public class GerenciamentoDeFilaController {

	@Inject private PontoDeAtendimentoDAO pontoDeAtendimentoDAO;
	@Inject private FilaDAO filaDAO;
	@Inject private ClienteDAO clienteDAO;
	@Inject private Result result;
	@Inject private UsuarioLogado usuarioLogado;
	
	@Permission
	@Get("/ponto/{idPonto}/dashboard")
	/**
	 * Mostra as informacoes sobre um ponto de atendimento. O metodo recebe o ponto como parametro
	 * @param idPonto
	 */
	public void dashboard(long idPonto) {
		PontoDeAtendimento ponto = pontoDeAtendimentoDAO.findById(idPonto);
		List<Cliente> clientesAtendidos = new ArrayList<>();
		List<Cliente> clientesEmEspera = new ArrayList<>();
		List<Cliente> proximo = new ArrayList<>();
		List<Cliente> clientesAtendidos1 = new ArrayList<>();
		List<Cliente> clientesEmEspera1 = new ArrayList<>();
		
		List<Cliente> clientes = new ArrayList<>();
		
		for (Fila fila : ponto.getFilas()) {
			clientes.addAll(fila.getClientes());
		}
		
		for(Cliente cliente: clientes) {
			if(cliente.getHorarioDeAtendimento() == null) {
				clientesEmEspera.add(cliente);
			} else {
				clientesAtendidos.add(cliente);
			}
		}
		
		Date date = new Date();
		int dia = date.getDate();
		int mes = date.getMonth();
		
		for (Cliente cliente2 : clientesEmEspera) {
			if(cliente2.getHorarioDeEntrada().getDate() == dia && cliente2.getHorarioDeEntrada().getMonth() == mes ) {
				clientesEmEspera1.add(cliente2);
			}
		}
		
		for (Cliente cliente2 : clientesAtendidos) {
			if(cliente2.getHorarioDeEntrada().getDate() == dia && cliente2.getHorarioDeEntrada().getMonth() == mes ) {
				clientesAtendidos1.add(cliente2);
			}
		}
		
		OrdenacaoGenerica.sortList(clientesEmEspera1, "horarioDeEntrada", SortType.ASC);
		OrdenacaoGenerica.sortList(clientesAtendidos1, "horarioDeAtendimento", SortType.DESC);
		
		Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
		
		TipoPrioridade tipoPrioridade = empresa.getTipoPrioridade();
		
		if(tipoPrioridade == TipoPrioridade.MEIO) {
			if(clientesAtendidos1.size() > 0) {
				if(clientesAtendidos1.get(0).getFila().isPrioritario()) {
					if(clientesEmEspera1.size() > 0) {
						proximo.add(clientesEmEspera1.get(0));
					}
				}else {
					for (Cliente cliente : clientesEmEspera1) {
						if(proximo.isEmpty() && cliente.getFila().isPrioritario()) {
							proximo.add(cliente);
						}
					}
					
				}
			}
			if(proximo.isEmpty() && !clientesEmEspera1.isEmpty()) {
				proximo.add(clientesEmEspera1.get(0));
			}
		}
		if(tipoPrioridade == TipoPrioridade.PRIORITARIO) {
			for (Cliente cliente : clientesEmEspera1) {
				if(proximo.isEmpty() && cliente.getFila().isPrioritario()) {
					proximo.add(cliente);
				}
			}
			
			if(proximo.isEmpty() && !clientesEmEspera1.isEmpty())
				proximo.add(clientesEmEspera1.get(0));
			
		}
		
		result.include("filas", ponto.getFilas());
		result.include("idPonto", idPonto);
		result.include("ponto", ponto);
		result.include("proximo", proximo);
		result.include("clientesAtendidos", clientesAtendidos1);
		result.include("clientesEmEspera", clientesEmEspera1);
	}
	
	@Permission
	@Post("/adicionar/cliente")
	/**
	 * O metodo adiciona o cliente a fila. Define o horario de entrada do cliente adicionado. Recebe o cliente, a fila 
	 * e o ponto de atendimento como parametro.
	 * @param cliente
	 * @param idFila
	 * @param idPonto
	 */
	public void adicionar(Cliente cliente, long idFila, long idPonto) {
		Date horarioDeEntrada = new Date();
		Fila fila = filaDAO.findById(idFila);
		cliente.setHorarioDeEntrada(horarioDeEntrada);
		cliente.setFila(fila);
		
		clienteDAO.save(cliente);
		
		result.include("mensagem", "Cliente adicionado com sucesso");
		result.redirectTo(GerenciamentoDeFilaController.class).dashboard(idPonto);
	}
	
	@Permission
	@Post("/atender/cliente")
	/**
	 * Realiza um atendimento em um ponto. Recebe o ponto como parametro.
	 * @param idPonto
	 */
	public void atender(long idPonto) {
		
		PontoDeAtendimento ponto = pontoDeAtendimentoDAO.findById(idPonto);
		List<Cliente> clientesAtendidos = new ArrayList<>();
		List<Cliente> clientesEmEspera = new ArrayList<>();
		List<Cliente> proximo = new ArrayList<>();
		List<Cliente> clientesAtendidos1 = new ArrayList<>();
		List<Cliente> clientesEmEspera1 = new ArrayList<>();
		
		List<Cliente> clientes = new ArrayList<>();
		
		for (Fila fila : ponto.getFilas()) {
			clientes.addAll(fila.getClientes());
		}
		
		for(Cliente cliente: clientes) {
			if(cliente.getHorarioDeAtendimento() == null) {
				clientesEmEspera.add(cliente);
			} else {
				clientesAtendidos.add(cliente);
			}
		}
		Date date = new Date();
		int dia = date.getDate();
		int mes = date.getMonth();
		
		for (Cliente cliente2 : clientesEmEspera) {
			if(cliente2.getHorarioDeEntrada().getDate() == dia && cliente2.getHorarioDeEntrada().getMonth() == mes ) {
				clientesEmEspera1.add(cliente2);
			}
		}
		
		for (Cliente cliente2 : clientesAtendidos) {
			if(cliente2.getHorarioDeEntrada().getDate() == dia && cliente2.getHorarioDeEntrada().getMonth() == mes ) {
				clientesAtendidos1.add(cliente2);
			}
		}
		
		OrdenacaoGenerica.sortList(clientesEmEspera1, "horarioDeEntrada", SortType.ASC);
		OrdenacaoGenerica.sortList(clientesAtendidos1, "horarioDeAtendimento", SortType.DESC);
		
		Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
		
		TipoPrioridade tipoPrioridade = empresa.getTipoPrioridade();
		
		if(tipoPrioridade == TipoPrioridade.MEIO) {
			if(clientesAtendidos1.size() > 0) {
				if(clientesAtendidos1.get(0).getFila().isPrioritario()) {
					if(clientesEmEspera1.size() > 0) {
						proximo.add(clientesEmEspera.get(0));
					}
				}else {
					for (Cliente cliente : clientesEmEspera1) {
						if(proximo.isEmpty() && cliente.getFila().isPrioritario()) {
							proximo.add(cliente);
						}
					}
					
				}
			}
			if(proximo.isEmpty() && !clientesEmEspera1.isEmpty()) {
				proximo.add(clientesEmEspera1.get(0));
			}
		}
		if(tipoPrioridade == TipoPrioridade.PRIORITARIO) {
			for (Cliente cliente : clientesEmEspera1) {
				if(proximo.isEmpty() && cliente.getFila().isPrioritario()) {
					proximo.add(cliente);
				}
			}
			
			if(proximo.isEmpty() && !clientesEmEspera1.isEmpty())
				proximo.add(clientesEmEspera1.get(0));
		}
		
		
		if(!proximo.isEmpty())
			proximo.get(0).setHorarioDeAtendimento(new Date());
		
		
		result.include("mensagem", "Cliente adicionado com sucesso");
		result.redirectTo(GerenciamentoDeFilaController.class).dashboard(idPonto);
	}
}