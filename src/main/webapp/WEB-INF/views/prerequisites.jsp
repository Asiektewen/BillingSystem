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
      <h2>Prerequisites</h2>
       <div class="row text-center">
		<table class="table table-bordered table-condensed">
			
			<tbody>
				<c:forEach items="${course1.prerequisites}" var="prereq" commandName="course1">
					<tr>
						<td><c:out value="${course1.prerequisites.title}" /></td>						
					

						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#editForm" 
								onclick="javascript:populateEditForm('${course.id}', '${course.title}')">
									<i class="fa fa-pencil"></i> Edit
								</button>

								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${course.id}', '${course.title}')">Delete
								</button>

							</nobr></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>            
            <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#addForm">Add Course</a>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  </body>
</html>