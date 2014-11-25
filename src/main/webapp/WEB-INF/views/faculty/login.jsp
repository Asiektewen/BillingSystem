<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  </head>

  <body>
    <div class="container">
      <h2>Login Form</h2>
      <c:url var="actionUrl" value="loginCheck" />
	<c:if test="${status == 'false'}">
			<p class="error">Please Try Again Username or Password Wrong</p>
		</c:if>
<form:form id="loginForm" commandName="customer" method="post"
	action="${actionUrl }" class="pure-form pure-form-aligned">

	<fieldset>
		<legend></legend>

		
		<div class="pure-control-group">
			<label for="code">Username</label>
			<form:input name="username" id = "username" path="username" placeholder="username" maxlength="15" />
		</div>
		
		<div class="pure-control-group">
			<label for="code">Password</label>
			<form:input type="password" name="password" id = "password" path="password" placeholder="password" maxlength="15" />
		</div>
		<!--  <div class="pure-control-group">
		  <form:select path="role" id="roleOptions">
	        <form:option value="">Login As:</form:option>
	        <form:option value="customer">Customer</form:option>
	        <form:option value="faculty">Faculty</form:option>
      </form:select>
      </div>-->
		
		<input type="submit" value="submit"/>

	</fieldset>
</form:form>
    </div>
<!--  It is advised to put the <script> tags at the end of the document body so they don't block rendering of the page -->
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/lib/jquery-1.10.2.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/lib/jquery-ui-1.10.4.custom.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/lib/jquery.ui.datepicker.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/js-for-listBooks.js"/>'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  </body>
</html>