<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>Customer Sections History</h1>

		<br>
	        <table class="table table-bordered table-condensed">			<thead>
				<tr>
					<th width="12%">Section Title</th>
					<th width="12%">Faculty</th>
					<th width="12%">Start Date</th>
					<th width="12%">End Date</th>
					<th width="12%">Status</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.enrollments}" var="enrollment" varStatus="loopCounter">
					<tr>
						<td><c:out value="${enrollment.section.course.title}" /></td>
						<td><c:out value="${enrollment.section.faculty.fullName}" /></td>
						<td><c:out value="${enrollment.section.startDate}" /></td>
						<td><c:out value="${enrollment.section.endDate}" /></td>
						<td><c:out value="${enrollment.enrollmentStatus}" /></td>
						
						
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
