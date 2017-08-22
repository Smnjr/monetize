$(function() {
	$('#login-form-link').click(function(e) {
		$('#msgError').remove();
		$('#login-form').delay(100).fadeIn(100);
		$('#register-form').fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#register-form-link').click(function(e) {
		$('#msgError').remove();
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
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
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
		submitHandler: function() {
			NProgress.start();
			$('#login-form').attr('action', '/monetize/j_security_check');
			$('#login-form').submit();
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

	
	$('#register-form').validate({
		submitHandler: function() {
			NProgress.start();
			$('#register-form').attr('action', '/monetize/salvarUsuario');
			$('#register-form').submit();
			NProgress.done();
		},

		rules : {
			username:{
				required:true,
				minlength: 5,
				maxlength: 20,
				remote: {
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					url: "/monetize/validarUsuario",
					type: "post",
					data: {
						username: function() {
							return $( "#username" ).val();
						}
					}
				},
				dataType: 'json'
			},
			email:{
				required:true,
				minlength: 10,
				maxlength: 30
			},
			pass:{
				required:true,
				minlength: 5,
				maxlength: 20
			},
			confirmacaoSenha:{
				required:true,
				minlength: 5,
				maxlength: 20,
				equalTo: "#pass"
			},

			messages: {
				confirmacaoSenha:{
					equalTo: "O valor da confirmação de senha deve ser igual ao valor da senha!"
				}
			}
		}
	});

//
//	$('#user').keyup(function(event) {
//		$('#userNameFormGroup-error').remove();
//		$('#msgError').remove();
//		disableSaveButton(false);
//		event.preventDefault();
//		if($('#user').val().length > 4){
//			var usuario = {}
//			usuario ["username"] = $('#user').val();
//
//			$.ajax({
//				type: "POST",
//				contentType: "application/json; charset=utf-8",
//				url: '/monetize/validarUsuario',
//				data: JSON.stringify(usuario),
//				timeout : 1000,
//				success : function(data) {
//					console.log("SUCCESS: ", data);
//				},
//
//				error : function(e) {
//					console.log("ERROR: ", e);
//					disableSaveButton(true);
//					display(e);
//				},
//
//				done : function(e) {
//					console.log("DONE");
//					disableSaveButton(false);
//				}
//			});
//		}
//	});
//
//
//	function display(data) {
//		if(data.status == 409){
//			console.log(data.responseText);
//			var resp =
//				"<label id=\"userNameFormGroup-error\" class= \"help-block \" for=\"userNameFormGroup\">"
//				+
//				data.responseText
//				+
//				"</label>";
//			$('#userNameFormGroup').append(resp);
//			$('#userNameFormGroup').removeClass('form-group has-success');
//			$('#userNameFormGroup').addClass('form-group has-error');
//		}
//		else{
//			var resp = "<div id=\"msgError\" class=\"alert alert-danger\" \">"
//				+data.responseText+"</div>";
//			$('#cnt').prepend(resp);
//		}
//	}
//
//	function disableSaveButton(flag) {
//		$("#register-submit").prop("disabled", flag);
//	}
});