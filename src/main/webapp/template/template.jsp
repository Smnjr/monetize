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
		<meta name="author" content="Dashboard">
	    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-responsive.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/nprogress-master/nprogress.css">
		
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery-3.2.1.js">  </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-validation/jquery.validate.js"> </script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-validation/localization/messages_pt_BR.js"> </script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui-1.9.2.custom.min.js">  </script>		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery.ui.touch-punch.min.js">  </script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-accordion/jquery.dcjqaccordion.2.7.js">  </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-scrollTo/jquery.scrollTo.min.js">  </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-nicescrool/jquery.nicescroll.js">  </script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/nprogress-master/nprogress.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common-scripts.js">  </script>



<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
	</head>

	<body>
	<section id="container">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		
		<!-- SideBar -->
		<tiles:insertAttribute name="sideBar" />

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper site-min-height">
				<!-- Body Page -->
				<tiles:insertAttribute name="body">
				</tiles:insertAttribute>
			</section>
		</section>
		<tiles:insertAttribute name="footer" />
	</section>
</body>
</html>
