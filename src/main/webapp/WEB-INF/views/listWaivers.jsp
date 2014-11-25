<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>List Of Sections</h1>

		<br>
	        <table class="table table-bordered table-condensed">			<thead>
				<tr>
					<th width="12%">Course Title</th>
					<th width="12%">Waiver Status</th>
					<th width="12%">Issued By</th>
					
					<th width="12%">Reason</th>
					<th width="12%">Feedback</th>
			
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.waivers}" var="waiver" varStatus="loopCounter">
					<tr>
						<td><c:out value="${waiver.course.title}" /></td>
						<td><c:out value="${waiver.waiverStatus}" /></td>
						<td><c:out value="${user.faculty.fullName}" /></td>
						
						<td><c:out value="${waiver.reason}" /></td>
						<td><c:out value="${waiver.feedback}" /></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
