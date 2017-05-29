$(function() {
				$('#login-form-link').click(function(e) {
					("#errorMessage").detach();
					$("#login-form").delay(100).fadeIn(100);
					$("#register-form").fadeOut(100);
					$('#register-form-link').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
				});
				
				$('#register-form-link').click(function(e) {
					("#errorMessage").detach();
					$("#register-form").delay(100).fadeIn(100);
					$("#login-form").fadeOut(100);
					$('#login-form-link').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
				});
	
				$("#register-submit").click(function(e) {
					NProgress.start();
					e.preventDefault();
					saveUsuario();
					NProgress.done();
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
	
				$("#login-form").validate({
					submitHandler: function() {
						NProgress.start();
						$('#login-form').attr('action', '/monetize/j_security_check');
						$('#login-form').submit();
						NProgress.done();
					},
					
					rules : {
						username : {
							required : true,
							minlength : 2,
							maxlength : 20
						},
						password : {
							required : true,
							minlength : 2,
							maxlength : 20
						}
					},
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
				
				function display(data) {
					var json = "<h4>Ajax Response</h4><pre>"
							+ JSON.stringify(data, null, 4) + "</pre>";
					$('#errorMessage').html(json);
				}
	
				function saveUsuario() {
					var usuario = {}
					usuario["username"] = $("#username").val();
					usuario["email"] = $("#email").val();
					usuario["password"] = $("#password").val();
					usuario["confirm-password"] = $("#confirm-password").val();
			
					$.ajax({
						type : "POST",
						contentType : "application/json",
						url : "/monetize/salvarUsuario",
						data : JSON.stringify(usuario),
						dataType : 'json',
						timeout : 100000,
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
				}
  			  });