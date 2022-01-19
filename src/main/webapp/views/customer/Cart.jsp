<%-- 
    Document   : Cart
    Created on : Oct 31, 2020, 9:42:21 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Giỏ Hàng</title>
<link id="favico" rel="icon" type="image/png"
	href="https://bizweb.dktcdn.net/100/327/577/files/2.png" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<style>
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}
</style>
<body>
	<%--<jsp:include page="pages/Home.jsp"></jsp:include>--%>
	<div class="shopping-cart">
		<div class="px-4 px-lg-0">
			<div class="pb-5">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

							<!-- Shopping cart table -->
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th scope="col" class="border-0 bg-light">
												<div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Đơn Giá</div>
											</th>
											<th style="width: 130px !important" scope="col"
												class="border-0 bg-light">
												<div class="py-2 text-uppercase">Số Lượng</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Xóa</div>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${productsIntoCart}" var="o"
											varStatus="loop">
											<tr>
												<th scope="row">
													<div class="p-2">
														<img src="${o.url}" alt="" width="70"
															class="img-fluid rounded shadow-sm">
														<div class="ml-3 d-inline-block align-middle">
															<h5 class="mb-0">
																<a style="font-size: 16px !important;"
																	href="productDetail?pid=${o.productId}"
																	class="text-dark d-inline-block">${o.description}</a>
															</h5>
															<span class="text-muted font-weight-normal font-italic"></span>
														</div>
													</div>
												</th>
												<td class="align-middle"><strong>${o.priceCurrent/1000}00</strong></td>
												<td class="align-middle"><button
														onclick="if(document.getElementById('quan${o.productId}').value !=1){changeDownQuanlityForProductIntoCart(${o.productId})}"
														style="width: 28px; height: 28px; border-radius: 14px; cursor: pointer;"
														class="btnSub">-</button> <input id="quan${o.productId}" type="number"
													style="width: 35px; height: 28px; outline: none; border-radius: 0; border: none; text-align: center; font-size: 14px;"
													value="${quanlityProductsCart.get(loop.index)}">
													<button onclick="changeUpQuanlityForProductIntoCart(${o.productId})"
														style="width: 28px; height: 28px; border-radius: 14px; cursor: pointer; margin-left: 4px;"
														class="btnAdd">+</button></td>
												<td class="align-middle"><a
													href="removeProductCart?pid=${o.productId}"
													class="text-dark">
														<button type="button" class="btn btn-danger">Delete</button>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- End -->
						</div>
					</div>

					<div class="row py-5 p-4 bg-white rounded shadow-sm">
						<div class="col-lg-6">
							<div
								class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
							<div class="p-4">
								<div class="input-group mb-4 border rounded-pill p-2">
									<input type="text" placeholder="Nhập Voucher"
										aria-describedby="button-addon3" class="form-control border-0">
									<div class="input-group-append border-0">
										<button id="button-addon3" type="button"
											class="btn btn-dark px-4 rounded-pill">
											<i class="fa fa-gift mr-2"></i>Sử dụng
										</button>
									</div>
								</div>
							</div>
						</div>
						<div id="thanhtoan" class="col-lg-6">
							<div
								class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành
								tiền</div>
							<div class="p-4">
								<ul class="list-unstyled mb-4">
									<li class="d-flex justify-content-between py-3 border-bottom"><strong
										class="text-muted">Tổng tiền hàng</strong><strong>${total}₫</strong></li>
									<li class="d-flex justify-content-between py-3 border-bottom"><strong
										class="text-muted">Phí vận chuyển</strong><strong>Free
											ship</strong></li>
									<li class="d-flex justify-content-between py-3 border-bottom"><strong
										class="text-muted">VAT</strong><strong>${vat}₫</strong></li>
									<li class="d-flex justify-content-between py-3 border-bottom"><strong
										class="text-muted">Tổng thanh toán</strong>
										<h5 class="font-weight-bold">${sum}₫</h5></li>
								</ul>
								<a href="order" class="btn btn-dark rounded-pill py-2 btn-block">Mua
									hàng</a>
							</div>
						</div>
					</div>
					<a href="${pid!=null?" productDetail?pid=":" home"}${pid!=null?pid:""}"><button
							type="button" class="btn btn-primary">Back</button></a>
				</div>
			</div>

		</div>

	</div>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		<script>
		function changeUpQuanlityForProductIntoCart(productId){ 
			var quanlity = Number(document.getElementById("quan"+productId).value)+1;
			document.getElementById("quan"+productId).value = quanlity;
            $.ajax({
            url: "/changeQuanlityForProductIntoCart",
            type:"get",
            data: {
            	"productId":productId,
            	"quanlity":quanlity
            },
            success: function(data){ 
            	var thanhtoan = document.getElementById("thanhtoan");
            	thanhtoan.innerHTML = data;
            },
            error: function (xhr){
               
            }
        });
    }
		
		function changeDownQuanlityForProductIntoCart(productId){ 
			var quanlity = Number(document.getElementById("quan"+productId).value)-1;
			document.getElementById("quan"+productId).value = quanlity;
            $.ajax({
            url: "/changeQuanlityForProductIntoCart",
            type:"get",
            data: {
            	"productId":productId,
            	"quanlity":quanlity
            },
            success: function(data){ 
            	var thanhtoan = document.getElementById("thanhtoan");
            	thanhtoan.innerHTML = data;
            },
            error: function (xhr){
               
            }
        });
    }
		</script>
</body>

</html>
