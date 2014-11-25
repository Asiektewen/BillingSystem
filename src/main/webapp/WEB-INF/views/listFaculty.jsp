 <%-- Author: Hiwot --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
    <div class="container">
       
        <div class="row text-center">
<h3>Faculty</h3>
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${faculties}" var="faculty" varStatus="loopCounter">
					<tr>
						<td><c:out value="${loopCounter.count}" /></td>
						<td><c:out value="${faculty.fullName}" /></td>
					

						<td><nobr>
								<button class="btn btn-primary" data-toggle="modal" data-target="#editForm" 
								onclick="javascript:populateEditForm('${faculty.id}', '${faculty.fullName}','${faculty.dob}','${faculty.joinDate}','${faculty.username}','${faculty.password}','${faculty.contactInformation}')">
									<i class="fa fa-pencil"></i> Edit
								</button>

								<button class="btn btn-primary" data-toggle="modal" data-target="#deleteForm" 
									onclick="javascript:populateDeleteForm('${faculty.id}', '${faculty.fullName}')">Delete
								</button>

							</nobr></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>            
            <a href="#" class="btn btn-lg btn-success" data-toggle="modal" data-target="#addForm">Add Faculty</a>
        </div>
    </div>

    <div class="modal fade" id="addForm" tabindex="-1" role="dialog" aria-labelledby="addForm" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Add Faculty</h4>
          </div>
          <div class="modal-body">
          
		<form method="POST" action="addfaculty" commandName="faculty">
			<div class="form-group">
	            <label>Full Name</label>
					<input class="input-xlarge" type="text" name="fullName"/>
	        </div>	
	        <div class="form-group">
	            <label>Date of Birth</label>
					<input class="datepicker" type="text" name="birth_date" placeholder="dd-mm-yyyy"/>
	        </div>	
	        <div class="form-group">
	            <label>Gender</label>
	            <select id="txtGender" class="input-large"  name="gender">
						<option value="0">Female</option>
						<option value="1">Male</option>
						</select>
	        </div>	
	        <div class="form-group">
	            <label>Join Date</label>
					<input class="datepicker" type="text" name="join_date" placeholder="dd-mm-yyyy"/>
	        </div>	
	        <div class="form-group">
	            <label>Contact Info</label>
					<input class="input-xlarge" type="text" name="contact_information"/>
	        </div>	<div class="form-group">
	            <label>User Name</label>
					<input class="input-xlarge" type="text" name="username"/>
	        </div>	
	        <div class="form-group">
	            <label>Password</label>		     	
					<input class="input-xlarge" type="text" name="password"/>
	        </div>	
		     <div class="modal-footer">
		     	            <input type="hidden" name="role" value="faculty"/>
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
            <h4 class="modal-title" id="myModalLabel">Edit Faculty</h4>
          </div>
          <div class="modal-body">       
			<form method="GET" action="editFaculty" id="editFormId">
				<div class="form-group">
		            <label>Full Name</label>
						<input id="txtFacultyId" type="hidden" name="facultyId"/>
						<input id="txtfullName" class="input-large" type="text" name="full_name" />
		        </div>
		        
		        <div class="form-group">
		            <label>Contact Info</label>
						<input id="txtContactInformation" class="input-large" type="text" name="contact_information" />
		        </div>
		        <div class="form-group">
		            <label>User Name</label>
						<input id="txtUserName" class="input-large" type="text" name="username" />
		        </div>
		        <div class="form-group">
		            <label>Password</label>
						<input id="txtPassword" class="input-large" type="text" name="password" />
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
            <h4 class="modal-title" id="myModalLabel">Delete Faculty</h4>
          </div>
          <div class="modal-body">       
			<form method="POST" action="deletefaculty" id="deleteFormId">
				<div class="form-group">
		            <label>Are you sure you want to delete </label>
						<input id="txtFacultyId" type="hidden" name="facultyId"/>
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
 	function populateEditForm(facultyId, fullName,dob,joinDate,username,password,contactInformation)
 	{
		$("input[id='txtFacultyId']" , "#editFormId")[0].value = facultyId;
		$("input[id='txtfullName']" , "#editFormId")[0].value = fullName;
		//$("input[id='txtBirthDate']" , "#editFormId")[0].value = dob;
		//$("input[id='txtJoinDate']" , "#editFormId")[0].value = joinDate;
		$("input[id='txtUserName']" , "#editFormId")[0].value = username;
		$("input[id='txtPassword']" , "#editFormId")[0].value = password;
		$("input[id='txtContactInformation']" , "#editFormId")[0].value = contactInformation;

 	}
 	function populateDeleteForm(facultyId, title)
 	{
		$("input[id='txtFacultyId']" , "#deleteFormId")[0].value = facultyId;
		$("input[id='txtTitle']" , "#deleteFormId")[0].value = title;
	} 
</script>
