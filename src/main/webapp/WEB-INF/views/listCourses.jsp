<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
    <div class="container">
       
        <div class="row text-center">
<h3>Courses</h3>
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Pre-requisites</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="course" varStatus="courseCounter">
					<tr>
						<td>${courseCounter.count}</td>
						<td>${course.title}</td>
						<td align="center">
							<c:if test="${course.prerequisites != null}"> 
								<c:if test="${fn:length(course.prerequisites)>0}">			 
									<a href="/studio/editPrerequisite/${course.id}">
										<c:forEach items= "${course.prerequisites}" var="prerequisite" varStatus="prerequisiteCounter">
											${prerequisite.title}, 
										</c:forEach>
									</a>
								</c:if>					
								<c:if test="${fn:length(course.prerequisites)==0}">			 
									<a href="/studio/editPrerequisite/${course.id}">
										<span class="glyphicon glyphicon-plus-sign"></span>
									</a>
								</c:if>					
																	
							</c:if>														
						</td>
					

						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#editForm" 
								onclick="javascript:populateEditForm('${course.id}', '${course.title}')">
									<i class="fa fa-pencil"></i> Edit
								</button>

								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${course.id}', '${course.title}')">Delete
								</button>

							</nobr>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>            
            <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#addForm">Add Course</a>
        </div>
    </div>

    <div class="modal fade" id="addForm" tabindex="-1" role="dialog" aria-labelledby="addForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Add Course</h4>
          </div>
          <div class="modal-body">
          
		<form method="POST" action="addcourse">
			<div class="form-group">
	            <label>Title</label>
					<input class="input-xlarge" type="text" name="title"/>
	        </div>			
	        <div class="form-group">
	          <label>Prerequisites</label>
	       
	        	</div>
		     <div class="modal-footer">
				<input class="btn btn-primary" type="submit" value="Submit">
		         <a href="#" class="btn" data-dismiss="modal">Close</a>
	  		</div>        		
		</form>


          </div> <!-- body -->

  		          
        </div>
      </div>
    </div>

     <div class="modal fade" id="editForm" tabindex="-1" role="dialog" aria-labelledby="editForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Edit Course</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="editcourse" id="editFormId">
				<div class="form-group">
		            <label>Title</label>
					<input id="txtCourseId" type="hidden" name="courseId"/>
					<input id="txtTitle" class="input-large" type="text" name="title" />
					<c:forEach items="${course.prerequisites}" var="prerequisite" varStatus="prerequisiteCounter">
						${prerequisite.title}, 
					</c:forEach>							
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
            <h4 class="modal-title" id="myModalLabel">Delete Course</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="deletecourse" id="deleteFormId">
				<div class="form-group">
		            <label>Are you sure to delete ?</label>
					<input id="txtCourseId" type="hidden" name="courseId"/>
					<input id="txtTitle" class="input-large" readonly="true" type="text" name="title" />
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

	<!-- end form div  --> 		          
 <script>
 	function populateEditForm(courseId, title)
 	{
		$("input[id='txtCourseId']" , "#editFormId")[0].value = courseId;
		$("input[id='txtTitle']" , "#editFormId")[0].value = title;
 	}
 	function populateDeleteForm(courseId, title)
 	{
		$("input[id='txtCourseId']" , "#deleteFormId")[0].value = courseId;
		$("input[id='txtTitle']" , "#deleteFormId")[0].value = title;
	} 
</script>
