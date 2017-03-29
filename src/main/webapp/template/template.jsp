<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    
		<title>SIACOD - Sistema de Apoio ao Combate a Dengue</title>
		
		<script type="text/javascript" src="resources/jquery/jquery-3.1.1.min.js">  </script>
		
		
	    <!-- Bootstrap Core CSS -->
		<link  href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	 	<!-- MetisMenu CSS -->
		<link  href="resources/metisMenu/metisMenu.css" rel="stylesheet">
		
		<!-- Custom CSS -->
		<link  href="resources/css/sb-admin-2.css" rel="stylesheet">
		
		<!-- Custom Fonts -->
		<link  href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		
		<!-- NProgress -->
		<link rel="stylesheet" 	 	href="resources/nprogress-master/nprogress.css">
		
		    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
		
		
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
				function logout() {
					$('#headerForm').attr("action", "${pageContext.request.contextPath}/logout");
					NProgress.start();
					$('#headerForm').submit();
					NProgress.done();
			}
		</script>
	</head>

	<body>
		<div id="wrapper">
		 	<!-- Navigation -->
			<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">	
			<!-- /.navbar-header -->
				
				<!-- Header -->
				<tiles:insertAttribute name="header" />
	
				<!-- Menu Page -->
				<tiles:insertAttribute name="sideBar" />
			</nav>
		
				<!-- Body Page -->
			<tiles:insertAttribute name="body" />
			    <!-- /#wrapper -->
			  
		</div>
		              
		<!-- js -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.js">   </script>
		<script type="text/javascript" src="resources/metisMenu/metisMenu.min.js"></script>
		<script type="text/javascript" src="resources/js/sb-admin-2.js"></script>
		<script type="text/javascript" src="resources/nprogress-master/nprogress.js">   </script>
	
	</body>
</html>