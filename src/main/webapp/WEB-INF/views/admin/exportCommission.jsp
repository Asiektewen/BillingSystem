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
			<div class="panel-heading">Monthly Commission</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>Month Picking</label>
							<div class="input-group date form_datetime col-md-4"
								data-date-format="mm-yyyy" data-link-format="mm-yyyy"
								data-link-field="dtp_input1">
								<input class="form-control" size="16" type="text" value=""
									readonly> <span class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<input type="hidden" id="dtp_input1" value="" /><br />
							<p class="help-block">Click the button on the right to select
								month.</p>
							<button type="button" id="genBtn" class="btn btn-primary">Generate
								Commission</button>
						</div>
						<div class="form-group col-md-4" id="result"></div>
					</div>
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
	$(document).ready(
			function() {
				$(".form_datetime").datetimepicker({
					format : "mm-yyyy",
					autoclose : true,
					startView : 3,
					minView : 3
				});
				$("#genBtn").click(
						function() {
							var month = $("#dtp_input1").val();
							// 			alert(month);
							$.ajax({
								url : '${adminContextPath}/salesrep/exportCommission',
								type : "post",
								data : {
									"month" : month
								},

								success : function(data) {
									$('#result').html("<div class='panel panel-info'><div class='panel-heading'>Result</div><div class='panel-body'>"
											+ data.content + "<a href='${adminContextPath}/file/download?fileID="+data.file+"'>"+data.fileName+"</a></div></div>");
								}
							});

						});
			});
</script>