<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>

	<a class="navbar-brand" href="${pageContext.request.contextPath}/">
		Monetize </a>
</div>

<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown nav-item open"> 
	<a
		class="nav-link dropdown-toggle" data-toggle="dropdown"
		href="javascript:;" aria-expanded="false">
		 <i class="fa fa-user fa-fw"></i> 
		 ${usuario} 
		 <b class="fa fa-caret-down"></b>
	</a>
	
		<ul class="dropdown-menu">
			<li class="dropdown-item"><a
				href="${pageContext.request.contextPath}/logout"> <i
					class="fa fa-sign-out fa-fw"></i> Sair
			</a></li>
		</ul> <!-- /.dropdown-user -->
	</li>
	<!-- /.dropdown -->
</ul>
<!-- /.navbar-top-links -->
