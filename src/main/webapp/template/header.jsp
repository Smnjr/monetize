<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>

	<a class="navbar-brand" href="${pageContext.request.contextPath}/"> Monetize </a>
</div>

<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<i class="fa fa-user fa-fw"></i>
			<i class="fa fa-caret-down"></i>
	</a>
		<ul class="dropdown-menu dropdown-user">
			<li>
				<a href="#">
					<i class="fa fa-user fa-fw"></i>
					Perfil do usu�rio
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="${pageContext.request.contextPath}/logout">
					<i class="fa fa-sign-out fa-fw"></i>
						Sair
				</a>
			</li>
		</ul>
		<!-- /.dropdown-user --></li>
	<!-- /.dropdown -->
</ul>
<!-- /.navbar-top-links -->
