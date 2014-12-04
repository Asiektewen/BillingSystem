<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"></h1>
		    <c:if test="${createCustomerMsg != null}">
			<div class="panel panel-success">
			    <div class="panel-heading"></div>
			    <div class="panel-body">
				Create Customer <c:out value="${createCustomerMsg}" />
				</div>
			</div>
			</c:if>
		<%
			request.getSession().setAttribute("createCustomerMsg", null);
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
						<form:form role="form" commandName="customer"
							action="${adminContextPath}/customer/save/" method="POST">

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
								<form:input id="street" name="street" type="text" path="street"
									placeholder="Street" class="form-control" required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="city"></label>
								<form:input id="city" name="city" type="text" placeholder="City"
									path="city" class="form-control" required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="zipcode"></label>
								<form:input id="zipcode" name="zipcode" type="text"
									path="zipCode" placeholder="ZipCode" class="form-control"
									required="" />

							</div>

							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="telephone"></label>
								<form:input id="telephone" name="telephone" type="text"
									path="phoneNumber" placeholder="Telephone" class="form-control"
									required="" />

							</div>
							<!-- Text input-->
							<div class="control-group">
								<label class="control-label" for="commissionlevel"></label>
								<form:input id="commissionlevel" name="commissionlevel"
									type="text" path="commissionlevel"
									placeholder="CommissionLevel" class="form-control" required="" />

							</div>

							<!-- Select Basic -->
							<div class="control-group">
								<label class="control-label" for="service"></label>
								<form:select path="serviceType" id="service" name="service"
									class="form-control">
									<option>Delure</option>
									<option>Spectrum</option>
								</form:select>
							</div>
							<!-- Select Basic -->
							<div class="control-group">
								<label class="control-label" for="country"></label>
								<form:select path="countryCode" id="country" name="country"
									class="form-control">
									<option>USA</option>
									<option>England</option>
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
<script type="text/javascript">
	var serviceJSON = ${serviceInfoJSON};
	var serviceKeys = ${serviceKeysJSON};
	$(document).ready(function() {
						var serviceHTML = "";
						var length = serviceKeys.length;
						for (var i = 0; i < length; i++) {
							if (i == 0) {
								serviceHTML += "<option value='"+serviceKeys[i]+"' selected>"
										+ serviceKeys[i] + "</option>";
							} else {

								serviceHTML += "<option value='"+serviceKeys[i]+"'>"
										+ serviceKeys[i] + "</option>";
							}

						}
						$("#service").html(serviceHTML);
						var countryHTML = "";

						var t = $("#service option:selected").val();
						console.log(t);
						var countries = serviceJSON[t];
						console.log(countries);
						var len = countries.length;
						for (var i = 0; i < len; i++) {
							console.log(countries[i].countryInfo.countryCode);
							countryHTML += "<option value='"+countries[i].countryInfo.countryCode+"'>"
									+ countries[i].countryInfo.countryName
									+ "</option>";
						}
						$("#country").html(countryHTML);
						$("#service").change(
										function() {
											countryHTML = "";
											var t = $(
													"#service option:selected")
													.val();
											var countries = serviceJSON[t];
											var len = countries.length;
											for (var i = 0; i < len; i++) {
												countryHTML += "<option value='"+countries[i].countryInfo.countryCode+"'>"
														+ countries[i].countryInfo.countryName
														+ "</option>";
											}
											$("#country").html(countryHTML);
										});
					});
</script>