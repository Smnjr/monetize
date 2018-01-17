<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="home" value="/" scope="request" />
<div id="page-wrapper" style="min-height: 120px;">
<div id="feedback" class=""alert alert-danger">
</div>
	<form id="user_edit_form" >
		<div class="row">
			<div class="col-lg-12">

				<h1 class="page-header">Perfil de usuário</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-10">
				<div class="panel panel-default">
					<div class="panel-heading">Informações do usuário:</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-8">
								<form id="register-form" name="registerForm" role="form" >
								
								<input type="hidden" id="id" name ="id" value="${usuario.id}"/>

									<div class="form-group" id="userFormGroup">
										<input type="text" name="username" tabindex="1" id="user"
											value="${usuario.username}" autocomplete="off"
											placeholder="Login do usuário" class="form-control"
											minlength="5" maxlength="20" required />

									</div>
									<div class="form-group" id="userNameFormGroup">
										<input type="text" name="nome" tabindex="1" id="name"
											autocomplete="off" placeholder="Nome do usu\u00e1rio"
											value="${usuario.nome}" class="form-control" minlength="5"
											maxlength="20" required />
									</div>

									<div class="form-group">
										<div class="input-group">
											<span class="input-group-addon">@</span> <input type="email"
												name="email" id="email" value="${usuario.email}"
												class="form-control" tabindex="2" placeholder="Email"
												required>
										</div>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="pass"
											value="${usuario.password}" class="form-control"
											autocomplete="off" tabindex="3" placeholder="Senha"
											minlength="5" maxlength="20" required />
									</div>

									<div class="form-group">
										<input type="password" name="confirmacaoSenha"
											value="${usuario.password}" id="confirmPassword"
											class="form-control" autocomplete="off" tabindex="4"
											placeholder="Confirmação de senha" minlength="5"
											maxlength="20" required />
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input name="register-submit" id="register-submit"
													type="submit" tabindex="5" class="btn btn-default"
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
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user.js"></script>
</div>


