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
          <span class="mdl-layout-title">${usuarioLogado.usuario.empresa.nome }</span>
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
      			<span>${usuarioLogado.usuario.nome}</span>
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
      	<form action="<c:url value="/unidade/adicionar"/>" method="post">
      		<span class="mdl-chip">
	    		<span class="mdl-chip__text">Adicionar Unidade</span>
			</span>
			<br>
			<div class="mdl-textfield mdl-js-textfield">
	      		<input class="mdl-textfield__input" type="text" id="nomeEmpresa" name="unidade.nome"/>
	    		<label class="mdl-textfield__label" for="nomeEmpresa">Nome da Unidade*</label>
	      	</div>
	      	<br>
	      	<div class="mdl-textfield mdl-js-textfield">
	      		<textarea class="mdl-textfield__input" rows="2" id="descricaoEmpresa" name="unidade.endereco"></textarea>
				<label class="mdl-textfield__label" for="descricaoEmpresa">Endereço da Unidade*</label>
			</div>
			<br>
			<div class="mdl-textfield mdl-js-textfield">
	      		<textarea class="mdl-textfield__input" rows="2" id="descricaoEmpresa" name="unidade.descricao"></textarea>
				<label class="mdl-textfield__label" for="descricaoEmpresa">Descrição da Unidade</label>
			</div>
			<br>
			<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored mdl-color-text--white">
	  			Adicionar
	  		</button>
      	</form>
      </main>
    </div>
    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  </body>
</html>