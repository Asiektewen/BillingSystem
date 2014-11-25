<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-9">
			 <h3>Confirm payment</h3>                    
				   <table class="table">
				   		<tbody>
				            <tr>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td align="center">
				                	<h4></h4>
				                </td>
				                <td align="right">
				                	<h4>Total $${user.shoppingCart.totalAmount} </h4>
				                </td>
				            </tr>	
						</tbody>	
					</table>
			<c:if test="${user.shoppingCart!=null }">			 
				   <table class="table">	            			   
				        <thead>
				            <tr>
				                <th>Image</th>
				                <th>Product Name</th>
				                <th>Unit</th>
				                <th>Quantity</th>
				                <th>Price</th>
				                <th>Amount</th>
				                <th></th>
				            </tr>
				        </thead>
				        <tbody>
							<c:forEach items="${user.shoppingCart.shoppingItems}" var="item" varStatus="loopCounter">								 
					            <tr>
					                <td><a href="detailProduct/${item.product.id}">
					                		<img src="${item.product.picurl}" />
					                	</a>
					                </td>
					                <td><a href="detailProduct/${item.product.id}">
					                		${item.product.name}
					                	</a></td>
					                <td>${item.product.unit}</td>
					                <td>${item.quantity}</td>
					                <td align="right">$${item.product.price}</td>
					                <td align="right">$${item.amount}</td>
					                <td>
					                	<a href="/studio/deleteShoppingCartItem/${item.id}" class="btn btn-primary" >Delete</a>
					                </td>
					            </tr>
							</c:forEach>							
				        </tbody>
				    </table>			
				   <table class="table">
				   		<tbody>
				            <tr>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td>
				                </td>
				                <td align="center">
				                	<h4></h4>
				                </td>
				                <td align="right">
				                	<h4>Total $${user.shoppingCart.totalAmount} </h4>
				                </td>
				            </tr>	
						</tbody>	
					</table>				
				<form method="POST" action="confirmShoppingCart" id="confirmForm">		
					<table style="width:100%">
					  <tr>
					    <td>
							<div class="form-group">
								<label>Shipping info</label>
								<textarea id="txtShippingInfo" name="shippingInfo">${user.shoppingCart.shippingInfo}</textarea>
					        </div>				
					    </td> 
					    <td>
							<div class="form-group">
								<label>Payment info</label>
								<textarea id="txtPaymentInfo" name="paymentInfo">${user.shoppingCart.paymentInfo}</textarea>
					        </div>				
					    </td>
					  </tr>
					</table>							
					<div class="form-group">
						<input class="btn btn-primary" type="submit" value="Confirm payment">
						<a href="/studio/browseProducts" class="btn btn-primary" >Continue shopping</a>
			        </div>								
				</form>
			</c:if>		
		</div>
	</div>
</div>
<!-- /.container -->
