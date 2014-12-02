<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"></h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Create Current Rates Excel</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<!-- Select Basic -->
						<div class="control-group">
							<label class="control-label" for="serviceType"></label> <select
								path="serviceType" id="service" name="serviceType"
								class="form-control">
								<option>Delure</option>
								<option>Spectrum</option>
							</select>
						</div>
						<!-- Select Basic -->
						<div class="control-group">
							<label class="control-label" for="countryNum"></label> <select
								path="countryNum" id="country" name="countryNum"
								class="form-control">
								<option>USA</option>
								<option>England</option>
							</select>
						</div>
						<!-- Button (Double) -->
						<div class="form-group" style="margin-top: 15px">
							<div class="row">
								<div class="col-lg-4">
									<button id="genBtn" name="genBtn" class="btn btn-info">Generate</button>
								</div>
							</div>
						</div>
					  <div class="form-group" style="margin-top: 15px"  id="result">
						</div>
					</div>
					<!-- /.col-lg-6 (nested) -->
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
	$(document)
			.ready(
					function() {
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
									+ countries[i].countryInfo.countryName + "</option>";
						}
						$("#country").html(countryHTML);
						$("#service").change(
										function() {
											countryHTML="";
											var t = $("#service option:selected").val();
											var countries = serviceJSON[t];
											var len = countries.length;
											for (var i = 0; i < len; i++) {
												countryHTML += "<option value='"+countries[i].countryInfo.countryCode+"'>"
														+ countries[i].countryInfo.countryName
														+ "</option>";
											}
											$("#country").html(countryHTML);
										});
						$("#genBtn").click(function(){
							console.log("Btn click");
						      var serviceType =$("#service option:selected").val();
						      var countryCode = $("#country option:selected").val();
						      var countryName= $("#country option:selected").text();
								$.ajax({
									url : '${adminContextPath}/rates/expCurrentRate',
									type : "post",
									data : {
										serviceType :serviceType,
										countryCode:countryCode,
										countryName:countryName
									},
									success : function(data) {
										$('#result').html("<div class='panel panel-info'><div class='panel-heading'>Result</div><div class='panel-body'>"
														+ data.content + "<a href='${adminContextPath}/file/download?fileID="+data.file+"'>"+data.fileName+"</a></div></div>");
									}
								});
						      
						      
						      
						});
					});
</script>