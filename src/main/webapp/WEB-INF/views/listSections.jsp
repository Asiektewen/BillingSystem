<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>List Of Sections</h1>

		<br>
	        <table class="table table-bordered table-condensed">			<thead>
				<tr>
					<th width="12%">Section Title</th>
					<th width="12%">Faculty</th>
					<th width="12%">Start Date</th>
					<th width="12%">End Date</th>
					<th width="12%">Seats</th>
					<!-- <th width="12%">Enrollments</th> -->
					<th width="12%">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sections}" var="section" varStatus="loopCounter">
					<tr>
						<td><c:out value="${section.course.title}" /></td>
						<td><c:out value="${section.faculty.fullName}" /></td>
						<td><c:out value="${section.startDate}" /></td>
						<td><c:out value="${section.endDate}" /></td>
						<td><c:out value="${section.seats}" /></td>
						<!-- <td><c:out value="${fn:length(section.enrollments)}" /></td> -->
						<td><nobr>
								
								<a class="pure-button pure-button-primary"
									href="enroll/${section.id}"> <i class="fa fa-times"></i>Enroll
								</a>

							</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
