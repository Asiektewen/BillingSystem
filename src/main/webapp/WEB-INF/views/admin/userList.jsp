<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"></h1>
		<c:if test="${message} not ">
			<div class="panel panel-success">
				<c:out value="${message }" />
			</div>
		</c:if>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Sales Represent List</div>
			<div class="panel-body">
				<div class="row">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Full Name</th>
								<th>Street</th>
								<th>City</th>
								<th>Username</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user" varStatus="loopCounter">
								<tr>
									<td><c:out value="${user.id}" /></td>
									<td><c:out value="${user.fullName}" /></td>
									<td><c:out value="${user.street}" /></td>
									<td><c:out value="${user.city}" /></td>
									<td><c:out value="${user.username}" /></td>
								</tr>
							</c:forEach>
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr> -->
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr> -->
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr>						<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr> -->
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr>						<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr> -->
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr>						<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr> -->
							<!-- 																<tr> -->
							<!-- 									<td>1</td> -->
							<!-- 									<td>John</td> -->
							<!-- 									<td>N 4th</td> -->
							<!-- 									<td>Fairfield</td> -->
							<!-- 									<td>52557</td> -->
							<!-- 									<td>6419542831</td> -->
							<!-- 									<td>USA</td> -->
							<!-- 									<td>Deluxe</td> -->
							<!-- 									<td>Le Zhang</td> -->
							<!-- 									<td>0.05</td> -->
							<!-- 								</tr>	 -->
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<nav>
							<ul class="pagination">
								<li
									<c:if test="${customerListPageInfo.page==1}"> class="disabled"</c:if>><a
									href="?page=<c:out value="${customerListPageInfo.page-1}" />"><span
										aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
								<c:forEach items="${customerListPageInfo.pageRange}" var="cpage"
									varStatus="loopCounter">
									<c:if test="${cpage==customerListPageInfo.page}">
										<li class="active"><a
											href="?page=<c:out value="${cpage }"/>"> <c:out
													value="${cpage }" /><span class="sr-only"><c:out
														value="${cpage }" /></span></a></li>
									</c:if>
									<c:if test="${cpage!=customerListPageInfo.page}">
										<li><a href="?page=<c:out value="${cpage }"/>"> <c:out
													value="${cpage }" /><span class="sr-only"><c:out
														value="${cpage }" /></span></a></li>
									</c:if>

								</c:forEach>
								<li
									<c:if test="${customerListPageInfo.page==customerListPageInfo.totalPage}"> class="disabled"</c:if>><a
									href="?page=<c:out value="${customerListPageInfo.page+1}" />"><span
										aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>




							</ul>
						</nav>
					</div>
					<div class="col-lg-2"></div>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
