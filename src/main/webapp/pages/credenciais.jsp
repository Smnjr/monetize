<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Monetize</title> 
	<script type="text/javascript" src="resources/jquery/jquery-3.1.1.min.js"> </script>
	<script type="text/javascript" src="resources//bootstrap/js/bootstrap.js"> </script>
	<script type="text/javascript" src="resources//jquery-validation/jquery.validate.js"> </script>
	<script type="text/javascript" src="resources/jquery-validation/localization/messages_pt_BR.js"> </script>
	<script type="text/javascript" src="resources/js/login.js">   </script>
	<script type="text/javascript" src="resources/nprogress-master/nprogress.js"> </script>
	<link   rel="stylesheet" id="bootstrap-css" href="resources/bootstrap/css/bootstrap.css">
	<link 	rel="stylesheet" href="resources/css/credenciais.css">
	<link 	rel="stylesheet" href="resources/nprogress-master/nprogress.css">
</head>

<body>
	<input type="hidden" id="mensagem" value="${mensagem.texto}" />

	<div class="container">
		<div class="${mensagem.tipoMensagem.classeCss}" role="alert">
				${mensagem.texto}
			</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<div class="panel panel-login">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<a href="#" class="active" id="login-form-link">Login</a>
								</div>
								<div class="col-xs-6">
									<a href="#" id="register-form-link">Register</a>
								</div>
							</div>
							<hr>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<form id="login-form" method="POST" role=form
										autocomplete="off" style="display: block;">
										<div class="form-group">
											<label for="username" class="col-sm-2 control-label"></label>
											<input type="text" name="username" id="username" tabindex="1"
												class="form-control" placeholder="Nome do usuário"
												minlength="2" maxlength="20" required>
										</div>

										<div class="form-group">
											<label for="password" class="col-sm-2 control-label"></label>
											<input type="password" name="password" id="password"
												tabindex="2" class="form-control" placeholder="Senha"
												autocomplete="off" minlength="2" maxlength="20" required>
										</div>

										<div class="form-group text-center">
											<input type="checkbox" tabindex="3" class=""
												"name="_spring_security_remember_me" id="remember">
											<label for="remember"> Remember Me</label>
										</div>

										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input type="submit" name="login-submit" id="login-submit"
														tabindex="3" class="form-control btn btn-login"
														value="Log In">
												</div>
											</div>
										</div>
									</form>
									<form id="register-form" name="registerForm" role="form"
										style="display: none;" method="post">
										<div class="form-group">
											<input type="text" name="username" tabindex="1" id="username"
												placeholder="Nome do usuário" class="form-control"
												minlength="2" maxlength="20" required />
										</div>

										<div class="form-group">
											<input type="email" name="email" id="email"
												class="form-control" tabindex="1" placeholder="Email"
												required />
										</div>

										<div class="form-group">
											<input type="password" name="password" id="pass"
												class="form-control" autocomplete="off" tabindex="2"
												placeholder="Senha" minlength="2" maxlength="20" required />
										</div>

										<div class="form-group">
											<input type="password" name="confirmacaoSenha"
												id="confirm-password" class="form-control"
												autocomplete="off" tabindex="2"
												placeholder="Confirmação de senha" minlength="2"
												maxlength="20" required />
										</div>

										<div class="form-group">
											<div class="row">
												<div class="col-sm-6 col-sm-offset-3">
													<input name="register-submit" id="register-submit"
														tabindex="4" class="form-control btn btn-register"
														value="Salvar">
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
	
</html>