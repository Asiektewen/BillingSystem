<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Yoga Studio</title>
    <!-- Bootstrap core CSS -->
    <link href="/studio/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/studio/resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="/studio/resources/css/custom.css" rel="stylesheet">
  </head>

  <body>
    <div class="navbar-wrapper">
      <div class="container">

        <div class="navbar navbar-inverse navbar-static-top" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Yoga Studio</a>
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
 <c:if test="${user.role == 'default'}">
              <jsp:include page="/resources/include/defaultmenu.jsp"/>
      </c:if>    
      <c:if test="${user.role == 'admin'}">
              <jsp:include page="/resources/include/adminmenu.jsp"/>
      </c:if>            
      <c:if test="${user.role == 'customer' || user.role == 'admin'}">
              <jsp:include page="/resources/include/customermenu.jsp"/>
      </c:if>            
      <c:if test="${user.role == 'faculty'  || user.role == 'admin'}">
              <jsp:include page="/resources/include/facultymenu.jsp"/>
      </c:if>            
              </ul>
                        <ul class="nav navbar-nav navbar-right" >
          
            <c:if test="${user.status == 'true'}">  
            <li><a href="/studio/editProfile"><span class="glyphicon glyphicon-user"></span>Welcome&nbsp; ${user.fullName} !</a></li>
            <li><a href="/studio/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
           </c:if>  
              
          </ul>
            </div>
          </div>
        </div>

      </div>
    </div>
  
    <!-- <div class="container">  -->
      		<sitemesh:write property='body' />           
    <!-- </div> -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="/studio/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/studio/resources/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/studio/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
