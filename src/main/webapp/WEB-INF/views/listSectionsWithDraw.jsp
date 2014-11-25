<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>You Can Withdraw Below Classes</h1>

		<br>
	        <table class="table table-bordered table-condensed">			<thead>
				<tr>
					<th width="12%">Section Title</th>
					<th width="12%">Faculty</th>
					<th width="12%">Start Date</th>
					<th width="12%">End Date</th>
					<th width="12%">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${withdrawEnrolls}" var="enrollment" varStatus="loopCounter">
					<tr>
						<td><c:out value="${enrollment.section.course.title}" /></td>
						<td><c:out value="${enrollment.section.faculty.fullName}" /></td>
						<td><c:out value="${enrollment.section.startDate}" /></td>
						<td><c:out value="${enrollment.section.endDate}" /></td>
						<td><nobr>
								
								<a class="pure-button pure-button-primary"
									href="deleteEnrollment/${enrollment.id}"> <i class="fa fa-times"></i>WithDraw
								</a>

							</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
