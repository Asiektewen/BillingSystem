<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"></h1>
		<c:if test="${createSalesRepMsg != null}">
			<div class="panel panel-success">
				<div class="panel-heading"></div>
				<div class="panel-body">
					Create Customer
					<c:out value="${createSalesRepMsg}" />
				</div>
			</div>
		</c:if>
		<%
			request.getSession().setAttribute("createSalesRepMsg", null);
		%>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Add Customer</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-3"></div>
					<div class="col-lg-6">
						<form:form role="form" commandName="salesRep"
							action="${adminContextPath}/salesrep/save/" method="POST">

							<!-- Form Name -->
							<legend>New Customer</legend>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="name"></label>
								<form:input id="name" name="name" path="fullName" type="text"
									placeholder="Full Name" class="form-control" required="" />
								<!-- 								<p class="help-block">First Name Middle Name Last Name</p> -->
							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="address"></label>
								<form:input id="address" name="address" type="text" path="address"
									placeholder="Adress" class="form-control" required="" />

							</div>



							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="phoneNumber"></label>
								<form:input id="phoneNumber" name="phoneNumber" type="text"
									path="phoneNumber" placeholder="phoneNumber" class="form-control"
									required="" />

							</div>
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="password"></label>
								<form:input id="password" name="password" type="text"
									path="password" placeholder="Password" class="form-control"
									required="" />

							</div>


							<!-- Button (Double) -->
							<div class="form-group" style="margin-top: 15px">
								<div class="row">
									<div class="col-lg-4">
										<button id="save" name="submit" class="btn btn-success">Save</button>
									</div>
									<div class="col-lg-4">
										<button id="reset" name="reset" class="btn btn-danger">Reset</button>
									</div>
								</div>
							</div>
						</form:form>

					</div>
					<div class="col-lg-3"></div>
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script type="text/javascript">
	
</script>