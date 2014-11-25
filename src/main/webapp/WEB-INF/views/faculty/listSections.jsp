<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>List Of Sections</h1>

		<br>
	        <table class="table table-bordered table-condensed">			<thead>
				<tr>
					<th width="12%">Section No.</th>
					<th width="12%">Course Name</th>
					<th width="12%">Faculty Name</th>
					<th width="12%">Section Seats</th>
					<th width="12%">Section Location</th>
					<th width="12%">Start Date</th>
					<th width="12%">End Date</th>
					<!-- <th width="12%">Enrollments</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sections}" var="section" varStatus="loopCounter">
					<tr>
						<td><c:out value="${section.id}" /></td>
						<td><c:out value="${section.course.title}" /></td>
						<td><c:out value="${section.faculty.fullName}" /></td>
						<td><c:out value="${section.seats}" /></td>
						<td><c:out value="${section.location}" /></td>
						<td><c:out value="${section.startDate}" /></td>
						<td><c:out value="${section.endDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
