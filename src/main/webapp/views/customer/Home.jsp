<%-- 
    Document   : Home
    Created on : Sep 16, 2021, 11:22:53 PM
    Author     : Hòa Đình
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='${pageContext.request.contextPath}/assets/css/base.css' />">
<link rel="stylesheet"
	href="<c:url value='${pageContext.request.contextPath}/assets/css/main.css' />">
<link rel="stylesheet"
	href="<c:url value='${pageContext.request.contextPath}/assets/css/grid.css' />">
<link rel="stylesheet"
	href="<c:url value='${pageContext.request.contextPath}/assets/css/responsive.css' />">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"
	integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link id="favico" rel="icon" type="image/png"
	href="https://bizweb.dktcdn.net/100/327/577/files/2.png" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<title>Shopee</title>
<link rel="stylesheet"
	href="<c:url value='${pageContext.request.contextPath}/assets/fonts/fontawesome-free-5.15.3/css/all.min.css' />">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<div class="wrapper">
		<!--        HEADER-->
		<jsp:include page="Header.jsp"></jsp:include>

		<div class="app__container">
			<div class="grid wide fix-wide-on-tablet">
				<div class="row app__content sm-gutter">
					<!-- List -->
					<div class="col p-2 t-3 m-0">
						<nav class="category">
							<h3 class="category__heading">Danh mục</h3>

							<ul class="category-list">
								<c:forEach items="${listC}" var="c">
									<li class="category-item ${tag == c.categoryId?"category-item--active":""}">
										<a href="category?cid=${c.categoryId}"
										class="category-item__link">${c.categoryName}</a>
									</li>
								</c:forEach>
							</ul>
						</nav>
					</div>

					<div id="resultSearch" class="col p-10 t-9 m-12">
						<div style="display:none;align-items: center;justify-content: center;flex-direction: column;margin:100px 0px 120px;" id="emptyResultSearch">
							<img style="width:134px;height:134px;"
								src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg//assets/a60759ad1dabe909c46a817ecbf71878.png">
							<div style="font-size:18px;font-family:'Helvetica Neue', Helvetica, Arial;margin:15px 0px 10px;">Không tìm thấy kết quả nào</div>
							<div style="font-size:18px;font-family:'Helvetica Neue', Helvetica, Arial;color: rgba(0,0,0,.54);">Hãy thử sử dụng các từ khóa chung chung hơn</div>
						</div>
						<div id="haveResultSearch">
							<!-- Filter -->
							<div class="home-filter hide-on-mobile-tablet">
								<span class="home-filter__label">Sắp xếp theo</span>
								<button class="home-filter__btn btn">Phổ biến</button>
								<button class="home-filter__btn btn btn--primary">Mới
									nhất</button>
								<button class="home-filter__btn btn">Bán chạy</button>

								<div class="select-input">
									<span class="select-input__label">Giá</span> <i
										class="select-input__icon fas fa-angle-down"></i>

									<ul class="select-input__list">
										<li class="select-input__item"><a href="#"
											class="select-input__link"> Giá: Thấp đến cao </a></li>
										<li class="select-input__item"><a href="#"
											class="select-input__link"> Giá: Cao đến thấp </a></li>
									</ul>
								</div>

								<div id="pagging-direction-top" class="home-filter__page">
									<span class="home-filter__page-num"> <span
										class="home-filter__page-current">${tagPage}</span>/${endPage}
									</span>

									<div id="LinkPaging" class="home-filter__page-control">
										<c:choose>
											<c:when test="${isSearch!=null}">
												<a href="search?txt=${txtS }&page=${tagPage-1 }"
													" ${tagPage==1?"id='leftArrow'":""}
													class="home-filter__page-btn ${tagPage==1?"home-filter__page-btn--disabled":""}">
													<i class="home-filter__page-icon fas fa-angle-left"></i>
												</a>
												<a a href="search?txt=${txtS }&page=${tagPage+1 }"
													" ${tagPage==endPage?"id='rightArrow'":""}
													class="home-filter__page-btn ${tagPage==endPage?"home-filter__page-btn--disabled":""}">
													<i class="home-filter__page-icon fas fa-angle-right"></i>
												</a>
											</c:when>
											<c:otherwise>
												<a href="${isCategory!=null?isCategory:"
													home?index"}=${tagPage-1 }
													" ${tagPage==1?"id='leftArrow'":""}
													class="home-filter__page-btn ${tagPage==1?"home-filter__page-btn--disabled":""}">
													<i class="home-filter__page-icon fas fa-angle-left"></i>
												</a>
												<a href="${isCategory!=null?isCategory:"
													home?index"}=${tagPage+1
										}
													" ${tagPage==endPage?"id='rightArrow'":""}
													class="home-filter__page-btn ${tagPage==endPage?"home-filter__page-btn--disabled":""}">
													<i class="home-filter__page-icon fas fa-angle-right"></i>
												</a>
											</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>

							<!-- Nav list mobile -->
							<nav class="mobile-category">
								<ul class="mobile-category__list">
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Thiết bị gia dụng sịn vc</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Dụng cụ điện thử vip lắm ă</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Đồ thể thao nam nữ</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Thiết bị gia dụng sịn vc</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Dụng cụ điện thử vip lắm ă</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Đồ thể thao nam nữ</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Thiết bị gia dụng sịn vc</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Dụng cụ điện thử vip lắm ă</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Đồ thể thao nam nữ</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Thiết bị gia dụng sịn vc</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Dụng cụ điện thử vip lắm ă</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Đồ thể thao nam nữ</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Thiết bị gia dụng sịn vc</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Dụng cụ điện thử vip lắm ă</a></li>
									<li class="mobile-category__item"><a href="#"
										class="mobile-category__link">Đồ thể thao nam nữ</a></li>
								</ul>
							</nav>

							<!-- Product -->
							<div class="home-product">

								<!-- grid->row->column -->
								<div id="content" class="row sm-gutter">
									<!-- Product Item -->
									<c:forEach items="${listP}" var="o">
										<div class="product col p-2-4 t-4 m-6">
											<a class="home-product-item"
												href="productDetail?pid=${o.productId}">

												<div class="home-product-item__img"
													style="background-image: url(${o.url});"></div>

												<h4 class="home-product-item__name">${o.description}</h4>

												<div class="home-product-item__price">
													<span class="home-product-item__price-old">${o.priceOld/1000}00đ</span>
													<span class="home-product-item__price-current">${o.priceCurrent/1000}00đ</span>
												</div>

												<div class="home-product-item__action">
													<!-- Liked: home-product-item__like--liked -->
													<span
														class="home-product-item__like home-product-item__like--liked">
														<i class="home-product-item__like-icon-empty far fa-heart"></i>
														<i class="home-product-item__like-icon-fill fas fa-heart"></i>
													</span>
													<div class="home-product-item__rating">
														<i class="home-product-item__star--gold fas fa-star"></i>
														<i class="home-product-item__star--gold fas fa-star"></i>
														<i class="home-product-item__star--gold fas fa-star"></i>
														<i class="home-product-item__star--gold fas fa-star"></i>
														<i class="fas fa-star"></i>
													</div>
													<span class="home-product-item__sold">${o.sold} đã
														bán</span>
												</div>
												<div class="home-product-item__origin">
													<span class="home-product-item__brand">Whoo</span> <span
														class="home-product-item__origin-name">${o.province}</span>
												</div>
												<div class="home-product-item__favorite">
													<i class="fas fa-check"></i> <span>Yêu thích</span>
												</div>
												<div class="home-product-item__sale-off">
													<span class="home-product-item__sale-off-percent">${o.saleOff}%</span>
													<span class="home-product-item__sale-off-label">GIẢM</span>
												</div>
											</a>
										</div>
									</c:forEach>
								</div>
							</div>
							<!--                        <button onclick="loadMore()" class="btn3"><span>Xem thêm</span></button>-->
							<ul id="pagging-direction"
								class="pagination pagination__home-product">
								<c:choose>
									<c:when test="${txtS!=null}">
										<li class="pagination-item ${tagPage==1?"invisible":""}"><a
											href="search?txt=${txtS }&page=${tagPage-1}"
											" class="pagination-item__link"> <i
												class="pagination-item__icon fas fa-angle-left"></i>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="pagination-item ${tagPage==1?"invisible":""}"><a
											href="${isCategory!=null?isCategory:"
											home?index"}=${tagPage-1
								}
											" class="pagination-item__link"> <i
												class="pagination-item__icon fas fa-angle-left"></i>
										</a></li>
									</c:otherwise>
								</c:choose>

								<c:forEach begin="1" end="${endPage}" var="i">
									<li class="pagination-item ${i==tagPage?"pagination-item--active":""}">
										<c:choose>
											<c:when test="${txtS!=null}">
												<a href="search?txt=${txtS
									}&page=${i}"
													class="pagination-item__link"> ${i} </a>
											</c:when>
											<c:otherwise>
												<a href="${isCategory!=null?isCategory:"
													home?index"}=${i
									} " class="pagination-item__link">
													${i} </a>
											</c:otherwise>
										</c:choose> <%-- <a href="${isCategory!=null?isCategory:"
									home?index"}=${i
									} " class="pagination-item__link">
										${i} </a> --%>
									</li>
								</c:forEach>
								<c:choose>
									<c:when test="${txtS!=null}">
										<li class="pagination-item ${tagPage==endPage?"invisible":""}"><a
											href="search?txt=${txtS }&page=${tagPage+1}"
											" class="pagination-item__link"> <i
												class="pagination-item__icon fas fa-angle-right"></i>
										</a></li>
									</c:when>
									<c:otherwise>
										<li class="pagination-item ${tagPage==endPage?"invisible":""}">
											<a href="${isCategory!=null?isCategory:"
											home?index"}=${tagPage+1 } " class="pagination-item__link">
												<i class="pagination-item__icon fas fa-angle-right"></i>
										</a>
										</li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>
