<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Welcome Aboard, ${user.fullName}.</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<c:if test="${user.role == 'ROLE_ADMIN'}">
<div class="row">

	<div class="col-lg-3 col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-sitemap fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${sCount}</div>
						<div>Sales Representative!</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-users fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${cCount }</div>
						<div>Customer!</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-rocket fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${rateCount }</div>
						<div>Rates!</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-6">
		<div class="panel panel-red">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<i class="fa fa-support fa-5x"></i>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">${uCount }</div>
						<div>Country!</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">Traffic Chart</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div id="morris-area-chart"></div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">Area Chart</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div id="morris-bar-chart"></div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
</c:if>
