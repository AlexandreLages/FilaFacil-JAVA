<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
          <span class="mdl-layout-title">Adicionar Empresa</span>
        </div>
      </header>
      <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
      </div>
      <main class="mdl-layout__content mdl-color--grey-100">
      	${mensagem }
      	<form action="<c:url value="/empresa/adicionar"/>" method="post">
      		<span class="mdl-chip">
    			<span class="mdl-chip__text">Dados da Empresa</span>
			</span>
			<br>
      		<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="text" id="nomeEmpresa" name="empresa.nome"/>
    			<label class="mdl-textfield__label" for="nomeEmpresa">Nome da Empresa*</label>
      		</div>
      		<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="text" id="cnpjEmpresa" name="empresa.cnpj"/>
    			<label class="mdl-textfield__label" for="cnpjEmpresa">CNPJ*</label>
      		</div>
      		<br>
      		<div class="mdl-textfield mdl-js-textfield">
			  <textarea class="mdl-textfield__input" rows="2" id="descricaoEmpresa" name="empresa.descricao"></textarea>
			  <label class="mdl-textfield__label" for="descricaoEmpresa">Descrição da Empresa</label>
			</div>
			<br>
			<span class="mdl-chip">
    			<span class="mdl-chip__text">Dados do Gerente</span>
			</span>
			<br>
			<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="text" id="nomeGerente" name="usuario.nome"/>
    			<label class="mdl-textfield__label" for="nomeGerente">Nome do Gerente*</label>
      		</div>
      		<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="text" id="emailGerente" name="usuario.email"/>
    			<label class="mdl-textfield__label" for="cnpjGerente">Email*</label>
      		</div>
      		<br>
      		<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="text" id="loginGerente" name="usuario.login"/>
    			<label class="mdl-textfield__label" for="loginGerente">Login*</label>
      		</div>
      		<div class="mdl-textfield mdl-js-textfield">
      			<input class="mdl-textfield__input" type="password" id="passwordGerente" name="usuario.password"/>
    			<label class="mdl-textfield__label" for="passwordGerente">Password*</label>
      		</div>
      		<br>
			<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
  				Adicionar
			</button>
			
      	</form>
      </main>
    </div>
    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  </body>
</html>