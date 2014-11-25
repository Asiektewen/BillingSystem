 <%-- Author: Hiwot --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Manage Products</title>

<link rel="stylesheet"
	href='<c:url value="/web-resources/css/pure-0.4.2.css"/>'>

<link rel="stylesheet"
	href='<c:url value="/web-resources/css/font-awesome-4.0.3/css/font-awesome.css"/>'>

<link rel="stylesheet"
	href='<c:url value="/web-resources/css/jquery-ui-1.10.4.custom.css"/>'>

<style type="text/css">
th {
	text-align: left
}
</style>


</head>

<body>
<div>
      
    <div class="container">
                <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#addForm">Add Product</a>
    
	  <div class="row text-center">

		

		<h3>Products</h3>

		<br>
		<table class="table table-bordered table-condensed" width="800">
			<thead>
				<tr>
				    <th width="12%">ID</th>
					<th width="25%">Image</th>
					<th width="20%">Name</th>
					<th width="12%">Price</th>
										<th width="12%">Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listProducts}" var="product" varStatus="loopCounter">
					<tr>
						<td><c:out value="${product.id}" /></td>
						<td><img src=${product.picurl} height="100" width="100"></td>
						<td><c:out value="${product.name}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#editForm" 
								onclick="javascript:populateEditForm('${product.id}', '${product.name}','${product.picurl}','${product.price}','${product.unit}')">
									<i class="fa fa-pencil"></i> Edit
								</button>

								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${product.id}', '${product.name}')">Delete
								</button>

							</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>
<div class="modal fade" id="addForm" tabindex="-1" role="dialog" aria-labelledby="addForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Add Product</h4>
          </div>
          <div class="modal-body">
          
		<form method="POST" action="addProduct" commandName="product">
			<div class="form-group">
			<label for="name">Product Name</label>
			<input name = "name" path="name"  placeholder="name" />
		</div>
		<div class="form-group">
			<label for="code">Picture URL</label>
			<input name="picurl" id = "picurl" path="picurl" placeholder="URL" />
		</div>
		
		<div class="form-group">
			<label for="code">Price</label>
			<input type="price" name="price" id = "price" path="price" placeholder="price" maxlength="15" />
		</div>
		<div class="form-group">
			<label for="dob">Unit</label>
			<input  name="unit" path="unit"
				placeholder="unit" />
		</div>
		<input path="id" type="hidden" />
		<input type="submit" value="submit"/>
		
		</form>
</div></div></div></div></div>
 <div class="modal fade" id="editForm" tabindex="-1" role="dialog" aria-labelledby="editForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Edit Faculty</h4>
          </div>
          <div class="modal-body">       
			<form method="GET" action="editProduct" id="editFormId">
				<div class="form-group">
		            <label>Name</label>
						<input id="txtProductId" type="hidden" name="productId"/>
						<input id="txtName" class="input-large" type="text" name="name" />
		        </div>
		        <div class="form-group">
		            <label>URL</label>
						<input id="txtPicurl" class="input-large" type="text" name="picurl" />
		        </div>
		        <div class="form-group">
		            <label>Price</label>
						<input id="txtPrice" class="input-large" type="text" name="price" />
		        </div>
		        <div class="form-group">
		            <label>Unit</label>
						<input id="txtUnit" class="input-large" type="text" name="unit" />
		        </div>
		       	
			     <div class="modal-footer">
					<input class="btn btn-primary" type="submit" value="Submit">
					<input class="btn btn-primary" type="button" data-dismiss="modal" value="Close">
		  		</div>        		
			</form>
          </div> 
        </div>
      </div>
    </div>
<div class="modal fade" id="deleteForm" tabindex="-1" role="dialog" aria-labelledby="deleteForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Delete Product</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="deleteProduct" id="deleteFormId">
				<div class="form-group">
		            <label>Are you sure you want to delete </label>
						<input id="txtProductId" type="hidden" name="productId"/>
						<input id="txtName" class="input-large" readonly="true" type="text" name="name" />
		        </div>	
			     <div class="modal-footer">
					<input class="btn btn-primary" type="submit" value="Submit">
					<input class="btn btn-primary" type="button" data-dismiss="modal" value="Close">
		  		</div>        		
			</form>
          </div> 
        </div>
      </div>
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
		<script>
 	function populateEditForm(productId, name,picurl,price,unit)
 	{
 		
		$("input[id='txtProductId']" , "#editFormId")[0].value = productId;
		$("input[id='txtName']" , "#editFormId")[0].value =name;
		$("input[id='txtPicurl']" , "#editFormId")[0].value = picurl;
		$("input[id='txtPrice']" , "#editFormId")[0].value = price;
		$("input[id='txtUnit']" , "#editFormId")[0].value = unit;
		
 	}
 	function populateDeleteForm(productId, name)
 	{
		$("input[id='txtProductId']" , "#deleteFormId")[0].value = productId;
		$("input[id='txtName']" , "#deleteFormId")[0].value = name;
	} 
</script>
</body>
</html>