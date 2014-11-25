 <%-- Author: Hiwot --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
    <div class="container">
       
        <div class="row text-center">
<h3>Sections</h3>
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th>ID</th>
					<th>Course</th>
										<th>Faculty</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Seats</th>
										<th>Location</th>
					
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sections}" var="section" varStatus="loopCounter">
					<tr>
						<td><c:out value="${section.id}" /></td>
						<td><c:out value="${section.course.title}" /></td>
						<td><c:out value="${section.faculty.fullName}" /></td>
												<td><c:out value="${section.startDate}" /></td>
												<td><c:out value="${section.endDate}" /></td>
												<td><c:out value="${section.seats}" /></td>
												<td><c:out value="${section.location}" /></td>
						
					

						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#editForm" 
								onclick="javascript:populateEditForm('${section.id}','${section.startDate}','${section.endDate}','${section.seats}','${section.location}')">
									<i class="fa fa-pencil"></i> Edit
								</button>

								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${section.id}')">Delete
								</button>

							</nobr></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>            
            <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#addForm">Add Section</a>
        </div>
    </div>

    <div class="modal fade" id="addForm" tabindex="-1" role="dialog" aria-labelledby="addForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Add Section</h4>
          </div>
          <div class="modal-body">
         
		<form:form method="POST"  commandName = "section" action="addSection"  >
			<div class="form-group">
	            <label>Course</label>
	            
	            <form:select  path="course.id" name="course" >
	            <form:options  items="${listCourses}" itemValue="id" itemLabel="title"/>	            
				</form:select>

	        </div>	
	        <div class="form-group">
	            <label>Faculty</label>
                 <form:select path="faculty.id" name="faculty">
	            <form:options  items="${listFaculty}" itemValue="id" itemLabel="fullName"/>	            
   
				</form:select>

	        </div>	
	        <div class="form-group">
	            <label>Start Date</label>
					<input class="datepicker" type="text" name="startDate" placeholder="dd-mm-yy"/>
	        </div>	
	        <div class="form-group">
	            <label>End Date</label>
					<input class="datepicker"type="text" name="endDate" placeholder="dd-mm-yy"/>
	        </div>	
	        <div class="form-group">
	            <label>Seats</label>
					<input class="input-xlarge" type="text" name="seats"/>
	        </div>	<div class="form-group">
	            <label>Location</label>
					<input class="input-xlarge" type="text" name="location"/>
	        </div>	
	       	
		     <div class="modal-footer">
				<input class="btn btn-primary" type="submit" value="Submit">
		         <a href="#" class="btn" data-dismiss="modal">Close</a>
	  		</div>        		
		</form:form>


          </div> <!-- body -->

  		          
        </div>
      </div>
    </div>

     <div class="modal fade" id="editForm" tabindex="-1" role="dialog" aria-labelledby="editForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Edit Section</h4>
          </div>
          <div class="modal-body">       






		<form:form method="POST"  commandName = "section" action="editSection" id="editFormId">
				<div class="form-group">
		            <label>Course</label>
						<input id="txtSectionId" type="hidden" name="sectionId"/>
                <form:select  path="course.id" name="course" >
	                <form:options  items="${listCourses}" itemValue="id" itemLabel="title"/>	            
				</form:select>		        </div>
		        <div class="form-group">
		            <label>Faculty</label>
                <form:select path="faculty.id" name="faculty">
	                <form:options  items="${listFaculty}" itemValue="id" itemLabel="fullName"/>	            
   
				</form:select>		        </div>
		        <div class="form-group">
		            <label>Start Date</label>
						<input id="txtStartDate" class="datepicker" type="text" name="start_date" placeholder="dd-mm-yy"/>
		        </div>
		        <div class="form-group">
		            <label>End Date</label>
						<input id="txtEndDate" class="datepicker" type="text" name="end_date" placeholder="dd-mm-yy"/>
		        </div>
		        <div class="form-group">
		            <label>Seats</label>
						<input id="txtSeats" class="input-large" type="text" name="seats" />
		        </div>
		        <div class="form-group">
		            <label>Location</label>
						<input id="txtLocation" class="input-large" type="text" name="location" />
		        </div>
		        	
			     <div class="modal-footer">
					<input class="btn btn-primary" type="submit" value="Submit">
					<input class="btn btn-primary" type="button" data-dismiss="modal" value="Close">
		  		</div>        		
              </form:form>          
              </div> 
        </div>
      </div>
    </div>
     <div class="modal fade" id="deleteForm" tabindex="-1" role="dialog" aria-labelledby="deleteForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Delete Section</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="deletesection" id="deleteFormId">
				<div class="form-group">
		            <label>Are you sure you want to delete this section?</label>
						<input id="txtSectionId" type="hidden" name="sectionId"/>
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
 	function populateEditForm(sectionId,startDate,endDate,seats,location)
 	{
 		
		$("input[id='txtSectionId']" , "#editFormId")[0].value = sectionId;
		$("input[id='txtStartDate']" , "#editFormId")[0].value = startDate;
		$("input[id='txtEndDate']" , "#editFormId")[0].value = endDate;
		$("input[id='txtSeats']" , "#editFormId")[0].value = seats;
		$("input[id='txtLocation']" , "#editFormId")[0].value = location;

 	}
 	function populateDeleteForm(sectionId)
 	{
		$("input[id='txtSectionId']" , "#deleteFormId")[0].value = sectionId;
	} 
</script>
