<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div style="width: 95%; margin: 0 auto;">



	<h1>List Of Advisees</h1>

	<br>
	<table class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th width="12%">Customer No.</th>
				<th width="12%">FullName</th>
				<th width="12%">BirthDay</th>
				<th width="12%">Gender</th>
				<th width="12%">Email</th>
				<th width="12%">Contact Info</th>
				<th width="12%">Join Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${customers}" var="customer"
				varStatus="loopCounter">
				<tr>
					<td><c:out value="${customer.id}" /></td>
					<td><c:out value="${customer.fullName}" /></td>

					<td><c:out value="${customer.dob}" /></td>
					<td><c:if test="${customer.gender==0}">
						MALE			</c:if> <c:if test="${customer.gender==1}">
						FEMALE			</c:if></td>
					<td><c:out value="${customer.email}" /></td>
					<td><c:out value="${customer.contactInformation}" />
						</td>
					<td><c:out value="${customer.joinDate}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
