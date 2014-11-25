<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<!-- Page Content -->
<div class="container">
    <div>
	    <div>
                <div>
                 <h3>Product detail</h3>
                    <div id="carousel-example-generic">
                    <c:if test="${product != null }">
						<table width="100%">
						    <tr>
							<td width="20%">
								<img src="${product.picurl}" />
							</td>
							<td width="40%">
								${product.name}<br>
								<label>Unit </label> ${product.unit}<br>
								<label>Price </label> $${product.price}
							</td>
							<td width="40%">								
					            <label>Items: ${fn:length(user.shoppingCart.shoppingItems)}</label><br>
					            <label>Total: $${user.shoppingCart.totalAmount} </label><br>
								<a href="/studio/addToShoppingCart/${product.id}" class="btn btn-primary" >Add to cart</a>
								<a href="/studio/browseShoppingCart" class="btn btn-primary" >Buy now</a>
								<a href="/studio/browseProducts" class="btn btn-primary" >Continue shopping</a>
							</td>
						    </tr>
						</table>
					</c:if>							
                    </div>
                </div>

            </div>
		</div>
	</div>
</div>
<!-- /.container -->
