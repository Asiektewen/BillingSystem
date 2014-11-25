<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<html>
  <head>
    <title>Yoga Studio</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
     <script src="https://code.jquery.com/jquery.js"></script> 
        <!-- Custom styles for this template -->
    <link href="/studio/resources/css/custom.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>                        
          </button>
          <a class="navbar-brand" href="/studio">Yoga Studio</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
<!--             <li class="active"><a href="/studio">Home</a></li> -->
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
    </nav>
  
    <div class="container">
      		<sitemesh:write property='body' />           
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  </body>
