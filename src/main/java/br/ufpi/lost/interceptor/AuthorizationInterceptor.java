/**
 * 
 */
package br.ufpi.lost.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.ufpi.lost.annotation.Public;
import br.ufpi.lost.controller.HomeController;
import br.ufpi.lost.model.UsuarioLogado;


@Intercepts
public class AuthorizationInterceptor {
	
	@Inject
	private UsuarioLogado usuarioLogado;
	
	@Inject
	private Result result;
	
	@Accepts
	public boolean accepts(ControllerMethod method) {
		return !method.containsAnnotation(Public.class);
	}
	
	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {

		
		if (!usuarioLogado.isLogado()) {
			result.redirectTo(HomeController.class).home();
			return;
		}
		stack.next();
	}
	
}
