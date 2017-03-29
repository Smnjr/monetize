$(document).ready(function(){
	$("#register-form").css("display", "none");
	$("#register-form").fadeIn(600);
	
	$("#login-form-link").click(function(event) {
		NProgress.start();
		linkLocation = this.href;   
		$("#register-form").fadeOut(500, redirectPage);
		$("#register-form-link").removeClass('active'); 
		$("#login-form-link").addClass('active');   
		NProgress.done();
	});
	
	$("#register-submit").click(function(event) {
		NProgress.start();
		$('#register-form').attr('action', '/monetize/salvarUsuario');
		$('#register-form').submit();
		NProgress.done();
	});

	$("#register-form-link").click(function(event) {
		NProgress.start();
		linkLocation = this.href;
		$("#login-form").fadeOut(500, redirectPage); 
		$("#login-form-link").removeClass('active');
		$("#register-form-link").addClass('active');
		NProgress.done();
	});

	function redirectPage() {
		window.location = linkLocation;
	}


	$.validator.setDefaults({
		errorElement: "label",
		errorClass: "help-block",
		highlight: function(element) {
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight: function(element) {
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
		},
		errorPlacement: function (error, element) {
			if (element.parent('.input-group').length || element.prop('type') === 'checkbox' || element.prop('type') === 'radio') {
				error.insertAfter(element.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});

	$("#register-form").validate({

		rules : {
			username:{
				required:true,
				minlength:2,
				maxlength: 20
			},
			email:{
				required:true,
				minlength:2,
				maxlength: 30
			},
			pass:{
				required:true,
				minlength:2,
				maxlength: 20,
			},
			confirmacaoSenha:{
				required:true,
				minlength:2,
				maxlength: 20,
				equalTo: "#pass"
			},
		},
		messages: {
			confirmacaoSenha:{
				equalTo: "O valor da confirmação de senha deve ser igual ao valor da senha!"
			}
		}

	});

});



