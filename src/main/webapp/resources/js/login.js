$(document).ready(
		function() {

			$(function() {

				$('#login-form-link').click(function(e) {
					$("#login-form").delay(100).fadeIn(100);
					$("#register-form").fadeOut(100);
					$('#register-form-link').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
				});
				$('#register-form-link').click(function(e) {
					$("#register-form").delay(100).fadeIn(100);
					$("#login-form").fadeOut(100);
					$('#login-form-link').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
				});

			});

			$.validator.setDefaults({
				errorElement : "label",
				errorClass : "help-block",
				highlight : function(element) {
					$(element).closest('.form-group')
							.removeClass('has-success').addClass('has-error');
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

			$("#login-form").validate({

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

		});
