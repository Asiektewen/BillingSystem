<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div style="width: 95%; margin: 0 auto;">



	<h1>List Of Waivers</h1>

	<br>
	<table class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th width="12%">Course Name</th>
				<th width="12%">Customer</th>
				<th width="12%">Status</th>
				<th width="12%">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${waivers}" var="waiver" varStatus="loopCounter">
				<tr>
					<td><c:out value="${waiver.course.title}" /></td>
					<td><c:out value="${waiver.customer.fullName}" /></td>
					<td title="Reason:<c:out value="${waiver.reason}"/>"><c:if test="${waiver.waiverStatus==0}">
						Waiting
						</c:if> <c:if test="${waiver.waiverStatus==1}">
						Approved
						</c:if> <c:if test="${waiver.waiverStatus==2}">
						<span >Rejected.</span></c:if></td>
		
					<td><nobr>
							<c:if test="${waiver.waiverStatus==0}">
								<a class="btn btn-primary"
									href="/studio/waivers/status/approve/${waiver.id}"> <i
									class="fa fa-times"></i>Approve
								</a>
								<a class="btn btn-warning" data-toggle="modal"
									data-target="#rejectForm"
									onclick="javascript:populateRejectForm('${waiver.id}')"> <i
									class="fa fa-times"></i>Rejected
								</a>
							</c:if>
							<c:if test="${waiver.waiverStatus==1}">
								<a class="btn btn-warning" data-toggle="modal"
									data-target="#rejectForm"
									onclick="javascript:populateRejectForm('${waiver.id}')"> <i
									class="fa fa-times"></i>Rejected
								</a>
							</c:if>
							<c:if test="${waiver.waiverStatus==2}">
								<a class="btn btn-primary"
									href="/studio/waivers/status/approve/${waiver.id}"> <i
									class="fa fa-times"></i>Approve
								</a>
							</c:if>
						</nobr></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<div class="modal fade" id="rejectForm" tabindex="-1" role="dialog"
	aria-labelledby="rejectForm" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myRejectModalLabel">Rejection
					Reason</h4>
			</div>
			<div class="modal-body">

				<form method="POST" action="/studio/waivers/status/reject">
					<div class="form-group">
						<textarea class="form-control" rows="3" name="reason" id="reason">
						</textarea>
					</div>
					<div class="modal-footer">
						<input class="btn btn-primary" type="submit" value="Submit">
						<a href="#" class="btn" data-dismiss="modal">Close</a>
					</div>
					<input type="hidden" name="rejectWaiverID" id="rejectWaiverID"
						value="">
				</form>


			</div>
			<!-- body -->


		</div>
	</div>
</div>

<!-- end form div  -->
<script>
	function populateRejectForm(waiverID) {
		$("#reason").val("");
		$('#rejectWaiverID').val(waiverID);
	}
</script>