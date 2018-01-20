<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/morris-0.4.3.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timeline.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/nprogress-master/nprogress.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-3.2.1.js">  </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources//jquery-validation/jquery.validate.js"> </script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-validation/localization/messages_pt_BR.js"> </script>


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation"
			style="margin-bottom: 0">
			<!-- /.navbar-header -->

			<!-- Header -->
			<tiles:insertAttribute name="header" />
			<!-- Menu Page -->
			<tiles:insertAttribute name="sideBar" />
		</nav>

		<!-- Body Page -->
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
			
			
			<!-- /#wrapper -->
	</div>
	<!-- js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/metisMenu/metisMenu.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/nprogress-master/nprogress.js"></script>

</body>
</html>