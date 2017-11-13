package br.ufpi.lost.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;

@Controller
public class EmpresaController {

	@Get
	public void teste() {
		System.out.println("Teste Vraptor");
	}
}