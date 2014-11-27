<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
		
		</h1>
		<c:if test="${message} not empty ">
		<div class="panel panel-success">
		<c:out value="${message }"/>
		</div>
		</c:if>
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
						<form:form role="form"  commandName="customer" action="${adminContextPath}/customer/save/"
							method="POST">

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
								<label class="control-label" for="street"></label>
								<form:input id="street" name="street" type="text" path="address" 
									placeholder="Street" class="form-control" required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="city"></label>
								<form:input id="city" name="city" type="text" placeholder="City" path="city" 
									class="form-control" required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="zipcode"></label>
								<form:input id="zipcode" name="zipcode" type="text" path="zipCode"
									placeholder="ZipCode" class="form-control" required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="telephone"></label>
								<form:input id="telephone" name="telephone" type="text"  path="phoneNumber"
									placeholder="Telephone" class="form-control" required="" />

							</div>
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="commissionlevel"></label>
								<form:input id="commissionlevel" name="commissionlevel" type="text"  path="commissionlevel"
									placeholder="CommissionLevel" class="form-control" required="" />

							</div>
							<!-- Select Basic -->
							<div class="control-group">
								<label class="control-label" for="country"></label> <form:select path="countryID"
									id="country" name="country" class="form-control">
									<option>USA</option>
									<option>England</option>
								</form:select>
							</div>

							<!-- Select Basic -->
							<div class="control-group">
								<label class="control-label" for="service"></label> <form:select path="serviceID"
									id="service" name="service" class="form-control">
									<option>Delure</option>
									<option>Spectrum</option>
								</form:select>
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
