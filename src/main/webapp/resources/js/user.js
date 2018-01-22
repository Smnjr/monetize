$(function() {
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

	$('#user_edit_form')
	.validate(
			{
				submitHandler : function() {
					$( '#mensagem' ).html( '' );
					NProgress.start();
					var data = {}
					data["confirmacaoSenha"] = $("#confirmPassword").val();
					data["password"] = $("#pass").val();
					data["email"] = $("#email").val();
					data["name"] = $("#name").val();
					data["username"] = $("#user").val();
					data["id"] = $("#id").val();

					$.ajax({
						type : "POST",
						contentType: 'application/json; charset=utf-8',
						url : "/monetize/editarPerfilUsuario",
						data : JSON.stringify(data),
						cache: false,
						timeout : 100000,
						success : function(data) {
							display(data,"alert alert-success");
						},
						error : function(e) {
							display(e.responseText,"alert alert-danger");
							console.log("ERROR: ", e);
						},
						done : function(e) {
							console.log("DONE", e.responseText);
						}
					});
					event.preventDefault();
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
		$('.row').prepend(div);
		$('#mensagem').addClass(classe);
		$('#mensagem').html(data);
	}
});