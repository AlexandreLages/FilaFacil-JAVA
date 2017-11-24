package br.ufpi.lost.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.ufpi.lost.annotation.Public;

@Controller
public class HomeController {

	@Public
	@Get("/")
	public void home(){}
}