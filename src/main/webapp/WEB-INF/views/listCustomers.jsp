<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



	<div style="width: 95%; margin: 0 auto;">

		

		<h1>List Of Customers</h1>

		<br>
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th width="12%">Name</th>
					<th width="12%">Username</th>
					<th width="12%">Dob</th>
					<th width="12%">Gender</th>
					<th width="12%">Enrollments</th>
					<th width="12%">Enrollments</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customers}" var="customer" varStatus="loopCounter">
					<tr>
						<td><c:out value="${customer.fullName}" /></td>
						<td><c:out value="${customer.username}" /></td>
						<td><c:out value="${customer.dob}" /></td>
						<td><c:out value="${customer.gender}" /></td>
						<td><c:out value="${fn:length(customer.enrollments)}" /></td>
						
						<td><nobr>
								<a class="pure-button pure-button-primary"
								
									href="editCustomer/${customer.id}"> <i class="fa fa-times"></i>Edit &nbsp;
								</a>

								<a class="pure-button pure-button-primary"
									href="deleteCustomer/${customer.id}"> <i class="fa fa-times"></i>Delete
								</a>

							</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	