<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<!-- Page Content -->
    <div class="row">
        <div class="col-md-9">

            <div class="row ">

                <div class="col-md-12">
			<form method="POST" action="searchProducts">
				<div class="form-group">
		            <label>Search</label>
					<input class="input-xlarge" type="text" name="searchText"/>
					<input class="btn btn-primary" type="submit" value="Go">
					<a href="/studio/browseShoppingCart">
						<img src="http://www.scottiescatalog.com/media/shopping-cart(1).png" width="80" height="60" />
					</a>
					<c:if test="${user.shoppingCart !=null }">
		            	<label>${fn:length(user.shoppingCart.shoppingItems)} items</label>-<label> $${user.shoppingCart.totalAmount} </label><br>
		            </c:if>						
		        </div>	
			</form>
                </div>

            </div>
			<c:if test="${listProducts!=null }">
				   <table class="table">
				        <thead>
				            <tr>
				                <th>Image</th>
				                <th>Product Name</th>
				                <th>Unit</th>
				                <th>Price</th>
				            </tr>
				        </thead>
				        <tbody>
							<c:forEach items="${listProducts}" var="product" varStatus="loopCounter">
					            <tr>
					                <td><a href="viewDetailProduct/${product.id}">
					                		<img src="${product.picurl}" />
					                	</a>
					                </td>
					                <td><a href="viewDetailProduct/${product.id}">
					                		${product.name}
					                	</a></td>
					                <td>${product.unit}</td>
					                <td>$${product.price}</td>
					            </tr>
							</c:forEach>
				        </tbody>
				    </table>			
			</c:if>						
		</div>
	</div>
<!-- /.container -->
