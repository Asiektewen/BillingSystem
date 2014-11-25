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
      <h2>Product Edit Form</h2>
      <c:url var="actionUrl" value="products/add" />

<form:form id="productForm" commandName="product"
	action="${actionUrl }" class="pure-form pure-form-aligned">

	<fieldset>
		<legend></legend>

		<div class="pure-control-group">
			<label for="name">Product Name</label>
			<form:input name = "name" path="name" placeholder="name" />
		</div>
		<div class="pure-control-group">
			<label for="code">Picture URL</label>
			<form:input name="picurl" id = "picurl" path="picurl" placeholder="URL" />
		</div>
		
		<div class="pure-control-group">
			<label for="code">Price</label>
			<form:input type="price" name="price" id = "price" path="price" placeholder="price" maxlength="15" />
		</div>
		<div class="pure-control-group">
			<label for="dob">Unit</label>
			<form:input  name="unit" path="unit"
				placeholder="unit"/>
		</div>
		<form:input path="id" type="hidden" />
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