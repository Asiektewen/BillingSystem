<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Update Rates</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:out value="${uploadMessage}" />
				<%
				       request.getSession().setAttribute("uploadMessage", "");
				%>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form" action="${adminContextPath}/file/fileupload"
							method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label>New Rates File</label> <input name="file" type="file"
									class="file" data-show-preview="false">
								<p class="help-block">System will process this file as soon
									as possible.</p>
							</div>
							<input type="hidden" name="function" value="updateRates" /> 
							<input type="hidden" name="param" value="123" />
						</form>
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
