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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
				    <c:if test="${result=='true'}">
					<div class="panel-heading">
						<h3 class="panel-title">Error</h3>
					</div>
					</c:if>
					<div class="panel-heading">
						<h3 class="panel-title">Billing Admin Portal</h3>
					</div>
					<div class="panel-body">
						<form role="form" action="${adminContextPath}/login" method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="username"
										name="username" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<!-- <div class="checkbox">
									<label> <input name="remember" type="checkbox"
										value="Remember Me">Remember Me
									</label>
								</div> -->
								<!-- Change this to a button or input when using this as a form -->
								<!-- <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a> -->
								<button type="submit" class="btn btn-lg btn-success btn-block" >Sign In</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="${adminResRoot}/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${adminResRoot}/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="${adminResRoot}/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${adminResRoot}/js/sb-admin-2.js"></script>

</body>

</html>
