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
      <h2>Customer Edit Form</h2>
      <c:url var="actionUrl" value="saveCustomer" />

<form:form id="customerForm" commandName="user" method="post"
	action="${actionUrl }" class="pure-form pure-form-aligned">

	<fieldset>
		<legend></legend>

		<div class="pure-control-group">
			<label for="name">Full Name</label>
			<form:input name = "fullName" path="fullName" placeholder="fullName" />
		</div>
		<div class="pure-control-group">
			<label for="code">Username</label>
			<form:input name="username" id = "username" path="username" readonly="true" placeholder="username" maxlength="15" />
		</div>
		
		<div class="pure-control-group">
			<label for="code">Password</label>
			<form:input type="password" name="password" id = "password" path="password" placeholder="password" maxlength="15" />
		</div>
		<div class="pure-control-group">
			<label for="dob">Birth Date</label>
			<form:input  name="dob" path="dob"
				placeholder="dd-MM-yyyy" class="datepicker" />
		</div>
		 <div class="pure-control-group">
		 <label for="dob">Gender</label>
		  <form:select path="gender" id="dob">
	        <form:option value="1">Male</form:option>
	        <form:option value="0">Female</form:option>
      </form:select>
      </div>
      <div class="pure-control-group">
			<label for="email">Email</label>
			<form:input name="email" id = "email" path="email"  placeholder="email" />
		</div>
      <div class="pure-control-group">
      <label for="address">Address</label>
      <form:textarea path="contactInformation" rows="5" cols="30" id="address"/>
      </div>
		<form:input path="id" type="hidden" />
		<input type="submit" value="save"/>

	</fieldset>
</form:form>
    </div>

  </body>
</html>