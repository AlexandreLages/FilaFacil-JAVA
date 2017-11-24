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
import br.ufpi.lost.model.Fila;
import br.ufpi.lost.model.PontoDeAtendimento;
import br.ufpi.lost.util.OrdenacaoGenerica;
import br.ufpi.lost.util.SortType;

@Controller
public class GerenciamentoDeFilaController {

	@Inject private PontoDeAtendimentoDAO pontoDeAtendimentoDAO;
	@Inject private FilaDAO filaDAO;
	@Inject private ClienteDAO clienteDAO;
	@Inject private Result result;
	
	@Permission
	@Get("/ponto/{idPonto}/dashboard")
	public void dashboard(long idPonto) {
		PontoDeAtendimento ponto = pontoDeAtendimentoDAO.findById(idPonto);
		List<Cliente> clientesAtendidos = new ArrayList<>();
		List<Cliente> clientesEmEspera = new ArrayList<>();
		
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
		
		OrdenacaoGenerica.sortList(clientesEmEspera, "horarioDeEntrada", SortType.ASC);
		OrdenacaoGenerica.sortList(clientesAtendidos, "horarioDeAtendimento", SortType.DESC);
		
		result.include("filas", ponto.getFilas());
		result.include("idPonto", idPonto);
		result.include("ponto", ponto);
		result.include("clientesAtendidos", clientesAtendidos);
		result.include("clientesEmEspera", clientesEmEspera);
	}
	
	@Permission
	@Post("/adicionar/cliente")
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
	public void atender(long idPonto) {
		PontoDeAtendimento ponto = pontoDeAtendimentoDAO.findById(idPonto);
		List<Cliente> clientes = new ArrayList<>();
		
		for (Fila fila : ponto.getFilas()) {
			clientes.addAll(fila.getClientes());
		}
		
		List<Cliente> clientesEmEspera = new ArrayList<>();
		
		for(Cliente cliente: clientes) {
			if(cliente.getHorarioDeAtendimento() == null) {
				clientesEmEspera.add(cliente);
			} 
		}
		
		OrdenacaoGenerica.sortList(clientesEmEspera, "horarioDeEntrada", SortType.ASC);
		
		if(!clientesEmEspera.isEmpty())
			clientesEmEspera.get(0).setHorarioDeAtendimento(new Date());
		
		result.include("mensagem", "Cliente adicionado com sucesso");
		result.redirectTo(GerenciamentoDeFilaController.class).dashboard(idPonto);
	}
}