<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="home" value="/" scope="request" />
<div class="form-panel">
	<h1 class="page-header">Perfil de usuário</h1>
	<hr>
	<div class="row">
		<form id="user_edit_form" class="form-horizontal style-form">
			<div class="col-md-8 personal-info">
				<input type="hidden" id="id" name="id" value="${usuario.id}" />

				<div class="form-group" id="userFormGroup">
					<label class="col-sm-2 col-sm-2 control-label"> <b>Nome
							do usuário</b>
					</label>
					<div class="col-sm-10">
						<input type="text" name="username" tabindex="1" id="user"
							value="${usuario.username}" autocomplete="off"
							placeholder="Login do usuário" class="form-control" minlength="5"
							maxlength="20" required />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label"> <b>Nome
							completo</b>
					</label>
					<div class="col-sm-10">
						<input type="text" name="nome" tabindex="1" id="name"
							autocomplete="off" placeholder="Nome do usu\u00e1rio"
							value="${usuario.nome}" class="form-control" minlength="5"
							maxlength="20" required />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 col-sm-2 control-label"> <b>Email</b>
					</label>
					<div class="col-sm-10">
						<div class="input-group">
							<span class="input-group-addon">@</span> <input type="email"
								name="email" id="email" value="${usuario.email}"
								class="form-control" tabindex="2" placeholder="Email" required>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label"> <b>Senha</b>
					</label>
					<div class="col-sm-10">
						<input type="password" name="password" id="pass"
							value="${usuario.password}" class="form-control"
							autocomplete="off" tabindex="3" placeholder="Senha" minlength="5"
							maxlength="20" required />
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label"> <b>Confirmação
							de senha</b>
					</label>
					<div class="col-sm-10">
						<input type="password" name="confirmacaoSenha"
							value="${usuario.password}" id="confirmPassword"
							class="form-control" autocomplete="off" tabindex="4"
							placeholder="Confirmação de senha" minlength="5" maxlength="20"
							required />
					</div>
				</div>

				<div class="form-group">
					<div class="modal-footer">
						<input name="register-submit" id="register-submit" type="submit"
							tabindex="5" class="btn btn-primary" value="Salvar"> <span></span>
						<input name="register-submit" id="register-submit" type="reset"
							tabindex="5" class="btn btn-default" value="Cancelar">
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/user.js"></script>

