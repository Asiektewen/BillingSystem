<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Telecom Billing Admin Portal</title>

<!-- Bootstrap Core CSS -->
<link href="${adminResRoot}/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${adminResRoot}/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${adminResRoot}/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${adminResRoot}/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${adminResRoot}/datetimepicker/bootstrap-datetimepicker.min.css"
	media="all" rel="stylesheet" type="text/css" />
<link href="${adminResRoot}/fileinput/fileinput.min.css" media="all"
	rel="stylesheet" type="text/css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="${adminResRoot}/js/jquery.js"></script>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Telecom Billing Admin
					System</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><c:out value="${user.username}" />
						<i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">

						<!--<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						 <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li> -->
						<li><a href="${adminContextPath}/logout"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<c:if test="${user.role == 'ROLE_ADMIN'}">

							<li><a href="${adminContextPath}"><i
									class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
							<li <c:if test="${currentPage == 'rates'}"> class="active" </c:if>><a
								href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Rates<span
									class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
<%-- 									<li><a href="${adminContextPath}/rates/expCurrent">Current --%>
<!-- 											Rates</a></li> -->
<%-- 									<li><a href="${adminContextPath}">Rates History</a></li> --%>
									<li><a href="${adminContextPath}/rates/updateRates">Update
											Rates</a></li>
									<li><a href="${adminContextPath}/rates/expSheet">Export
											Rates Sheet</a></li>
									<li><a href="${adminContextPath}/rates/createCurrentRates">Create Current
											Rates</a></li>
									<li><a href="${adminContextPath}/rates/expTraffic">Export
											Traffic Summary</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li
								<c:if test="${currentPage == 'callDetails'}"> class="active"</c:if>><a
								href="#"><i class="fa fa-table fa-fw"></i> Call
									Details<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="${adminContextPath}/callDetails/upload/">Upload
											Call Details</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li
								<c:if test="${currentPage == 'customer'}"> class="active"</c:if>><a
								href="#"><i class="fa fa-child fa-fw"></i>
									Customers<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="${adminContextPath }/customer/list/">Customer List</a></li>
									<li><a href="${adminContextPath}/customer/create/">Create Customer</a></li>
									<li><a href="${adminContextPath}/customer/genBill">Generate
											Bill</a></li>
								</ul> <!-- /.nav-second-level --></li>
<%-- 							<li <c:if test="${currentPage == 'sales'}"> class="active"</c:if>><a --%>
<!-- 								href="#"><i class="fa fa-sitemap fa-fw"></i> Sales -->
<!-- 									Reprentatives<span class="fa arrow"></span></a> -->
<!-- 								<ul class="nav nav-second-level"> -->
<!-- 									<li><a href="#">Sales Representatives List</a></li> -->
<!-- 									<li><a href="#">Create Sales Representative</a></li> -->
<!-- 								</ul> /.nav-second-level</li> -->
						</c:if>
						<c:if test="${user.role == 'ROLE_SALESREP'}">

							<li><a href="index.html"><i
									class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
							<li
								<c:if test="${currentPage == 'customer'}"> class="active"</c:if>><a
								href="#"><i class="fa fa-child fa-fw"></i>
									Customers<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="${adminContextPath }/customer/list/">Customer List</a></li>
									<li><a href="${adminContextPath}/customer/create/">Create Customer</a></li>
									<li><a href="${adminContextPath}/customer/genBill">Generate
											Bill</a></li>
								</ul> <!-- /.nav-second-level --></li>
						</c:if>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper">
			<sitemesh:write property='body' />
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->



	<!-- Bootstrap Core JavaScript -->
	<script src="${adminResRoot}/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="${adminResRoot}/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${adminResRoot}/js/sb-admin-2.js"></script>
	<!-- fileinput plugin -->
	<script src="${adminResRoot}/fileinput/fileinput.min.js"
		type="text/javascript"></script>
	<!--  datepicker plugin -->
	<script
		src="${adminResRoot}/datetimepicker/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
</body>

</html>

