$(function() {
				$('#login-form-link').click(function(e) {
					$('#errorMessage').remove();
					$('#login-form').delay(100).fadeIn(100);
					$('#register-form').fadeOut(100);
					$('#register-form-link').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
				});
				
				$('#register-form-link').click(function(e) {
					$('#errorMessage').remove();
					$('#register-form').delay(100).fadeIn(100);
					$('#login-form').fadeOut(100);
					$('#login-form-link').removeClass('active');
					$(this).addClass('active');
					enableSearchButton(true);
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
							maxlength: 20
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
					},
					messages: {
						confirmacaoSenha:{
							equalTo: "O valor da confirmação de senha deve ser igual ao valor da senha!"
						}
					}
				});


				$('#user').blur(function(e) {
					var usuario = {}
					 usuario ["username"] = $('#user').val();
					if($('#user').val().length > 4){
						$.ajax({
							type: "POST",
							contentType : "application/json",
							url: '/monetize/validarUsuario',
							data: JSON.stringify(usuario),
							dataType: 'json',

							success : function(data) {
								console.log("SUCCESS:", data);
								display(data);
								enableSearchButton(false);
							},
							error : function(e) {
								console.log("ERROR: ", e);
								display(e);
								enableSearchButton(true);
							},
							done : function(e) {
								console.log("DONE");
								enableSearchButton(false);
							}
						});
					}
				});


				function display(data) {
					var json = 
						"<label>"+ JSON.stringify(data, null, 4)+"</label>"
						$('#user').html(json);
				}
				
				function enableSearchButton(flag) {
					$("#register-submit").prop("disabled", flag);
				}
});