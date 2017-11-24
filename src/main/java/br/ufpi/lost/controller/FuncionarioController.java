package br.ufpi.lost.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.ufpi.lost.annotation.Permission;
import br.ufpi.lost.annotation.Public;
import br.ufpi.lost.dao.EmpresaDAO;
import br.ufpi.lost.dao.UsuarioDAO;
import br.ufpi.lost.model.Empresa;
import br.ufpi.lost.model.Usuario;
import br.ufpi.lost.model.UsuarioLogado;

@Controller
public class FuncionarioController {
	
	@Inject private UsuarioLogado usuarioLogado;
	@Inject private Result result;
	@Inject private EmpresaDAO dao;
	@Inject private UsuarioDAO usuarioDAO;
	
	@Permission
	@Get
	public void adicionar() {
		
	}
	
	@Permission
	@Get
	public void telaInicial() {
			Empresa empresa = usuarioLogado.getUsuario().getEmpresa();
			List<Usuario> funcs = dao.listarFuncionariosPorEmpresa(empresa.getId());
			result.include("funcionarios", funcs);
	}
	
	@Public
	@Post("/funcionario/adicionar")
	public void adicionar(Usuario usuario) {
		
		usuario.setEmpresa(usuarioLogado.getUsuario().getEmpresa());
		
		usuarioDAO.save(usuario);
		
		result.include("mensagem", "Empresa adicionada com sucesso");
		result.redirectTo(this).telaInicial();
	}

}
