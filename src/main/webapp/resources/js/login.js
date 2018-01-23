$(function() {
	$('#login-form-link').click(function(e) {
		$('#mensagem').remove();
		$('#login-form').delay(100).fadeIn(100);
		$('#register-form').fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#register-form-link').click(function(e) {
		$('#mensagem').remove();
		$('#register-form').delay(100).fadeIn(100);
		$('#login-form').fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$.validator.setDefaults({
		errorElement : "label",
		errorClass : "help-block",
		highlight : function(element) {
			$(element).closest('.form-group').removeClass('has-success')
			.addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error')
			.addClass('has-success');
		},
		errorPlacement : function(error, element) {
			if (element.parent('.input-group').length
					|| element.prop('type') === 'checkbox'
						|| element.prop('type') === 'radio') {
				error.insertAfter(element.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});

	$('#login-form').validate({
		submitHandler : function(form) {
			$('#mensagem').remove();
			var data = {}

			data["password"] = $("#password").val();
			data["username"] = $("#username").val();

			NProgress.start();

			$.ajax({
				type : "POST",
				contentType: 'application/json',
				url : "/monetize/login",
				data : JSON.stringify(data),
				timeout : 10000,
				error :function(e) {
					display(e.responseText,"alert alert-danger");
					console.log("ERROR: ", e);
				},
				success: function(result) {
					$(location).attr('href',"/monetize/home");
				}
			});
			NProgress.done();
		},

		rules : {
			username : {
				required : true,
				minlength : 5,
				maxlength : 20
			},
			password : {
				required : true,
				minlength : 5,
				maxlength : 20
			}
		},
	});

	$('#register-form')
	.validate(
			{
				submitHandler : function(e) {
					$('#mensagem').remove();
					var data = {}
					data["confirmacaoSenha"] = $("#confirmPassword").val();
					data["password"] = $("#pass").val();
					data["email"] = $("#email").val();
					data["nome"] = $("#name").val();
					data["username"] = $("#user").val();
					NProgress.start();
					$.ajax({
						type : "POST",
						contentType: 'application/json',
						url : "/monetize/salvarUsuario",
						data : JSON.stringify(data),
						timeout : 1000,
						error :function(e) {
							display(e.responseText,"alert alert-danger");
							console.log("ERROR: ", e);
						},
						 success: function(result) {
							  $(location).attr('href',"/monetize/home");
					        }
					});
					NProgress.done();
				},

				rules : {
					username : {
						required : true,
						minlength : 5,
						maxlength : 20,
						remote : {
							url : "/monetize/isUsernameValido",
							type : "post",
							error : function(e) {
								display(e.responseText,"alert alert-danger");
								console.log("ERROR: ", e);
							}
						},
					},
					name : {
						required : true,
						minlength : 5,
						maxlength : 30
					},
					email : {
						required : true,
						minlength : 10,
						maxlength : 30
					},
					pass : {
						required : true,
						minlength : 5,
						maxlength : 20
					},
					confirmacaoSenha : {
						required : true,
						minlength : 5,
						maxlength : 20,
						equalTo : "#pass"
					}
				},
				messages : {
					confirmacaoSenha : {
						equalTo : "O valor da confirmação de senha deve ser igual ao valor da senha!"
					},
					username : {
						remote : "Nome de usuário existente, informe outro nome!"
					}
				}
			});
	
	function display(data, classe) {
		var div = '<div id="mensagem"></div>';
		$('.panel-body').prepend(div);
		$('#mensagem').addClass(classe);
		$('#mensagem').html(data);
	}
});