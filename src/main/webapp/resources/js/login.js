$(document).ready(function(){
	$("#login-form").css("display", "none");
	$("#login-form").fadeIn(600);

	$("#login-form-link").click(function(event) {
		NProgress.start();
		linkLocation = this.href;   
		$("#register-form").fadeOut(500, redirectPage);
		$("#register-form-link").removeClass('active'); 
		$("#login-form-link").addClass('active');
		NProgress.done();
	});

	$("#login-submit").click(function(event) {
		NProgress.start();
		$('#login-form').attr('action', '/monetize/j_security_check');
		$('#login-form').submit();
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

	$("#login-form").validate({

		rules : {
			username:{
				required:true,
				minlength:2,
				maxlength: 20
			},
			password:{
				required:true,
				minlength:2,
				maxlength: 20
			}
		},
	});

});




