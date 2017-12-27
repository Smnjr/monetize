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
					NProgress.start();
					var data = {}

					data["user"] = $("#user").val();
					data["name"] = $("#name").val();
					data["email"] = $("#email").val();
					data["pass"] = $("#pass").val();
					data["confirmPassword"] = $("#confirmPassword").val();

					$.ajax({
						type : "PUT",
						URL : "/monetize/editar",
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : 'json',
						timeout: 10000,
						success : function(data) {
							console.log("SUCCESS: ", data);
							display(data);
						},
						error : function(e) {
							console.log("ERROR: ", e);
							display(e);
						},
						done : function(e) {
							console.log("DONE");
						}
					});
					NProgress.done();
					return false;
				},

				rules : {
					username : {
						required : true,
						minlength : 5,
						maxlength : 20,
						remote : {
							url : "/monetize/isUsernameValido",
							type : "post",
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
	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
			+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
});