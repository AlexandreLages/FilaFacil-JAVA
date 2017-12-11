<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>FilaFácil</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>">
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.amber-blue.min.css" />
    <link rel="stylesheet" href="<c:url value="/administrador/styles.css"/>">
    <link rel="stylesheet" href="<c:url value="/administrador/estilos.css"/>">
    <style>
    #view-source {
      position: fixed;
      display: block;
      right: 0;
      bottom: 0;
      margin-right: 40px;
      margin-bottom: 40px;
      z-index: 900;
    }
    </style>
  </head>
  <body>
  	<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
      <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
          <span class="mdl-layout-title">${usuarioLogado.usuario.empresa.nome } - ${ponto.nome}</span>
          <div class="mdl-layout-spacer"></div>
          <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
            <i class="material-icons">more_vert</i>
          </button>
          <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
            <li class="mdl-menu__item"><a href="${linkTo[LoginController].logout }">Sair</a></li>
          </ul>
        </div>
      </header>
      <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
      	<header class="demo-drawer-header">
      		<div class="demo-avatar-dropdown">
      			<span>${usuarioLogado.usuario.nome} - ${usuarioLogado.usuario.empresa.nome}</span>
      			<div class="mdl-layout-spacer"></div>
      		</div>
      	</header>
      	<nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
      		<a class="mdl-navigation__link" href="${linkTo[UnidadeController].telaInicial}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">business</i>Unidades</a>
      	
      		<a class="mdl-navigation__link" href="${linkTo[PontoDeAtendimentoController].telaInicial}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">location_on</i>Pontos de Atendimento</a>
      	
      		<a class="mdl-navigation__link" href="${linkTo[FilaController].telaInicial}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">linear_scale</i>Filas</a>
      	
      		<a class="mdl-navigation__link" href="${linkTo[FuncionarioController].telaInicial}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Funcionarios</a>
      		      	
      		<a class="mdl-navigation__link" href="${linkTo[PontoDeAtendimentoController].associacoes}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">settings_ethernet</i>Associar Ponto a Unidade</a>
      	
      		<a class="mdl-navigation__link" href="${linkTo[FilaController].associacoes}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">settings_ethernet</i>Associar Fila a Ponto</a>
      	
      		
      		<a class="mdl-navigation__link" href="${linkTo[EmpresaController].telaConfiguracao}"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">brightness_high</i>Configurações</a></nav>
      </div>
      <main class="mdl-layout__content mdl-color--grey-100">
      	<c:if test="${mensagem != null }">
      	<span class="mdl-chip">
    		<span class="mdl-chip__text">${mensagem}</span>
		</span>	
		</c:if>
		<br>
		<div class="row">
			<div class="col-md-6">
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Adicionar Cliente</button>
			</div>
			<div class="col-md-6">
			<form action="<c:url value="/atender/cliente"/>" method="post">
			<input class="mdl-textfield__input" type="hidden" id="nomeEmpresa" name="idPonto" value="${ponto.id}"/>
				<button type="submit" class="btn btn-primary btn-lg">Atender Cliente</button>
			</form>
			</div>
		
		</div>
		<div class="row">
		<span class="mdl-chip">
    		<span class="mdl-chip__text">Próximo a ser atendido</span>
		</span>	
		<br>
				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="margin:auto;" width="80%">
					<thead>
    					<tr>
      						<th class="mdl-data-table__cell--non-numeric">Cliente</th>
      						<th class="mdl-data-table__cell--non-numeric">CPF</th>
      						<th class="mdl-data-table__cell--non-numeric">Fila</th>
      						<th class="mdl-data-table__cell--non-numeric">Prioridade</th>
      						<th>Horário de entrada</th>
    					</tr>
  					</thead>
  					<tbody>
  						<c:forEach items="${proximo}" var="cliente" varStatus="status">
  						<tr>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.nome }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.cpf }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.fila.descricao }</td>
  							<c:if test="${cliente.fila.prioritario == true }">
  							<td class="mdl-data-table__cell--non-numeric">Sim</td>
  							</c:if>
  							<c:if test="${cliente.fila.prioritario == false}">
  							<td class="mdl-data-table__cell--non-numeric">Não</td>
  							</c:if>
  							<td>${cliente.horarioDeEntrada.hours}:${cliente.horarioDeEntrada.minutes}:${cliente.horarioDeEntrada.seconds}</td>
  						</tr>
  						</c:forEach>
  					</tbody>
				</table>
		</div>
		
		
		<div class="row">
			<div class="col-md-6">
				<span class="mdl-chip">
    		<span class="mdl-chip__text">Fila de Espera</span>
		</span>	
		<br>
				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="margin:auto;" width="80%">
					<thead>
    					<tr>
      						<th class="mdl-data-table__cell--non-numeric">Cliente</th>
      						<th class="mdl-data-table__cell--non-numeric">CPF</th>
      						<th class="mdl-data-table__cell--non-numeric">Fila</th>
      						<th class="mdl-data-table__cell--non-numeric">Prioridade</th>
      						<th>Horário de entrada</th>
    					</tr>
  					</thead>
  					<tbody>
  						<c:forEach items="${clientesEmEspera }" var="cliente" varStatus="status">
  						<tr>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.nome }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.cpf }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.fila.descricao }</td>
  							<c:if test="${cliente.fila.prioritario == true }">
  							<td class="mdl-data-table__cell--non-numeric">Sim</td>
  							</c:if>
  							<c:if test="${cliente.fila.prioritario == false}">
  							<td class="mdl-data-table__cell--non-numeric">Não</td>
  							</c:if>
  							<td>${cliente.horarioDeEntrada.hours}:${cliente.horarioDeEntrada.minutes}:${cliente.horarioDeEntrada.seconds}</td>
  						</tr>
  						</c:forEach>
  					</tbody>
				</table>
			</div>
			<div class="col-md-6">
			<span class="mdl-chip">
    		<span class="mdl-chip__text">Fila de Atendidos</span>
		</span>	
		<br>
				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="margin:auto;" width="80%">
					<thead>
    					<tr>
      						<th class="mdl-data-table__cell--non-numeric">Cliente</th>
      						<th class="mdl-data-table__cell--non-numeric">CPF</th>
      						<th class="mdl-data-table__cell--non-numeric">Fila</th>
      						<th class="mdl-data-table__cell--non-numeric">Prioridade</th>
      						<th>Horário de atendimento</th>
    					</tr>
  					</thead>
  					<tbody>
  						<c:forEach items="${clientesAtendidos }" var="cliente" varStatus="status">
  						<tr>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.nome }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.cpf }</td>
  							<td class="mdl-data-table__cell--non-numeric">${cliente.fila.descricao }</td>
  							<c:if test="${cliente.fila.prioritario == true }">
  							<td class="mdl-data-table__cell--non-numeric">Sim</td>
  							</c:if>
  							<c:if test="${cliente.fila.prioritario == false}">
  							<td class="mdl-data-table__cell--non-numeric">Não</td>
  							</c:if>
  							<td>${cliente.horarioDeAtendimento.hours}:${cliente.horarioDeAtendimento.minutes}:${cliente.horarioDeAtendimento.seconds}</td>
  						</tr>
  						</c:forEach>
  					</tbody>
				</table>
			</div>
		</div>
      </main>
      <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Informações do Cliente</h4>
        </div>
        <div class="modal-body">
        	<form action="<c:url value="/adicionar/cliente"/>" method="post">
        		<span class="mdl-chip">
    				<span class="mdl-chip__text">Dados do Cliente</span>
				</span>
				<br>
				
				<div class="mdl-textfield mdl-js-textfield">
      				<input class="mdl-textfield__input" type="text" id="nomeEmpresa" name="cliente.nome"/>
    				<label class="mdl-textfield__label" for="nomeEmpresa">Nome*</label>
      			</div>
      			<br>
      			<div class="mdl-textfield mdl-js-textfield">
      				<input class="mdl-textfield__input" type="text" id="nomeEmpresa" name="cliente.cpf"/>
    				<label class="mdl-textfield__label" for="nomeEmpresa">CPF*</label>
      			</div>
      			<br>
      			<span class="mdl-chip">
    				<span class="mdl-chip__text">Filas</span>
				</span>
				<br>
				<select name="idFila">
					<c:forEach var="fila" items="${filas}">
						<option id="${fila}" value="${fila.id}">${fila}</option>
					</c:forEach>
				</select>
				<br><br>
				<input class="mdl-textfield__input" type="hidden" id="nomeEmpresa" name="idPonto" value="${ponto.id}"/>
				<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
  					Adicionar
				</button>
        	</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
    </div>
    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  </body>
</html>