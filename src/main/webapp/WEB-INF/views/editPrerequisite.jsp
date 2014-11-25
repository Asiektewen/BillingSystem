<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
    <div class="container">
       
        <div class="row text-center">
		<h3>Edit Pre-requisites:<strong>${course.title}</strong></h3> 					
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th>No</th>
					<th>Title</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${course.prerequisites}" var="prerequisite" varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${prerequisite.title}</td>
					
						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${course.id}', '${prerequisite.id}', '${prerequisite.title}')">Delete
								</button>

							</nobr>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>       
		
		<table class="table table-bordered table-condensed">
			
			<tbody>

				<tr>
					<th>
			<label>Select a Prerequisite to add</label>   
					
					</th>
					<th>
			<select class="form-control" name="selectPrerequisite" id="selectPrerequisiteId" >          
				<c:forEach var="prerequisite" items="${remainPrerequisites}">
					<option value="${prerequisite.id}" >${prerequisite.title}</option> 
				</c:forEach>            
			</select>         
					
					</th>
					<th>
			<button class="btn btn-primary"  onclick="javascript:populateAddPrerequisite()">
				<i class="fa fa-pencil"></i> Add pre-requisite
			</button>
			<a href="/studio/listCourses" class="btn btn-primary" >Back to Course</a>        
					
					</th>
				</tr>
			</tbody>
		</table>       

        </div>
    </div>

     <div class="modal fade" id="deleteForm" tabindex="-1" role="dialog" aria-labelledby="deleteForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Delete Pre-requite</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="/studio/deleteprerequisite" id="deleteFormId">
				<div class="form-group">
		            <label>Are you sure to delete ?</label>
						<input id="txtCourseId" type="hidden" name="courseId"/>
						<input id="txtPrerequisiteId" type="hidden" name="prerequisiteId"/>
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
 	function populateDeleteForm(courseId, prerequisiteId, title)
 	{
		$("input[id='txtCourseId']" , "#deleteFormId")[0].value = courseId;
		$("input[id='txtPrerequisiteId']" , "#deleteFormId")[0].value =prerequisiteId ;
		$("input[id='txtTitle']" , "#deleteFormId")[0].value = title;
	} 
 	function populateAddPrerequisite()
 	{
 		var courseId= '${course.id}';
 		var prerequisiteId = $('#selectPrerequisiteId' ).val();
 		window.location.href = '/studio/addPrerequisite/' + courseId + '/' + prerequisiteId;
	} 
</script>
