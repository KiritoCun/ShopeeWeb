<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="footer">
            <div class="grid wide fix-wide-on-tablet footer-size">
                <!-- Footer list -->
                <div class="row">
                    <div class="col p-2-4 t-4 m-6">
                        <h3 class="footer__heading">Chăm sóc khách hàng</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Trung tâm trợ giúp</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">F8-Shop Mail</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Hướng dẫn mua hàng</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col p-2-4 t-4 m-6">
                        <h3 class="footer__heading">Giới thiệu</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Giới thiệu</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Tuyển dụng</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Điều khoản</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col p-2-4 t-4 m-6">
                        <h3 class="footer__heading">Danh mục</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Trang phục nữ</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Mĩ phẫm nam giới</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Mĩ phẫm nữ giới</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col p-2-4 t-4 m-6">
                        <h3 class="footer__heading">Theo dõi</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fab fa-facebook"></i>
                                    Facebook
                                </a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fab fa-instagram"></i>
                                    Instagram
                                </a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fab fa-linkedin"></i>
                                    Linkedin
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col p-2-4 t-8 m-12">
                        <h3 class="footer__heading">Vào cửa hàng trên ứng dụng</h3>
                        <div class="footer__download">
                            <img src="<c:url value='/assets/img/qr_code.png'/>" alt="Download QR" class="footer__download-qr">
                            <div class="footer__download-apps">
                                <a href="#" class="footer__download-app-link">
                                    <img src="<c:url value='/assets/img/app_store.png'/>" alt="Google Play" class="footer__download-app-img">
                                </a>
                                <a href="#" class="footer__download-app-link">
                                    <img src="<c:url value='/assets/img/google_play.png'/>" alt="App Store" class="footer__download-app-img">
                                </a>
                                <a href="#" class="footer__download-app-link">
                                    <img src="<c:url value='/assets/img/app_gallery.png'/>" alt="App Gallery" class="footer__download-app-img">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Footer copyright -->
            <div class="footer__bottom">
                <div class="grid wide fix-wide-on-tablet">
                    <p class="footer__copyright-text">© 2021 - Bản quyền thuộc về Công ty LDH</p>
                </div>
            </div>
                <div class="modal ${active}">
        <div class="modal__overlay"></div>
        <div class="modal__body"> 
<!--             Register form -->
             <div class="auth-form form-register">
                 <form action="home" method="POST" class="auth-form__container">
                    <div class="auth-form__header">
                        <h3 class="auth-form__heading">Đăng kí</h3>
                        <span class="auth-form__switch-btn" onclick="login()">Đăng nhập</span>
                    </div>
                    <div class="auth-form__form">
                        <div class="auth-form__group">
                            <input name="username" type="text" class="auth-form__input" placeholder="Email của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input name="password" type="password" class="auth-form__input" placeholder="Mật khẩu của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input name="repassword" type="password" class="auth-form__input" placeholder="Nhập lại mật khẩu của bạn">
                        </div>
                    </div>

                    <div class="auth-form__aside">
                        <p class="auth-form__policy-text">
                            Bằng việc đăng kí, bạn đã đồng ý với F8 Shop về
                            <a href="#" class="auth-form__text-link">Điều khoản dịch vụ</a> &
                            <a href="#" class="auth-form__text-link">Chính sách bảo mật</a>
                        </p>
                    </div>

                    <div class="auth-form__controls">
                        <button id="hideBlock1" class="btn btn--normal auth-form__controls-back" onclick="hideForm();">TRỞ LẠI</button>
                        <button type="submit" value="register" name="action" class="btn btn--primary">ĐĂNG KÍ</button>
                    </div>
                </form>

                <div class="auth-form__socials">
                    <a href="#" class="auth-form__socials--facebook btn btn--size-s btn--width-icon">
                        <i class="auth-form__socials-icon fab fa-facebook-square"></i>
                        <span class="auth-form__social-title">
                            Kết nối với facebook
                        </span>
                    </a>
                    <a href="#" class="auth-form__socials--google btn btn--size-s btn--width-icon">
                        <i class="auth-form__socials-icon fab fa-google auth-form__socials-icon--google-color"></i>
                        <span class="auth-form__social-title">
                            Kết nối với google
                        </span>
                    </a>
                </div>
            </div>

<!--             Login form -->
             <div class="auth-form form-login ${active}">
                 <form action="home" method="POST" class="auth-form__container">
                    <div class="auth-form__header">
                        <h3 class="auth-form__heading">Đăng nhập</h3>
                        <span class="auth-form__switch-btn" onclick="register()">Đăng kí</span>
                    </div>
                    <div class="auth-form__form">
                        <p style="color:red;">${annouce}</p>
                        <div class="auth-form__group">
                            <input name="user" type="text" value="${cookie['userNameC'].value}" class="auth-form__input" placeholder="Email của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input name="pass" type="password" value="${cookie['passwordC'].value}" class="auth-form__input" placeholder="Mật khẩu của bạn">
                        </div>                      
                        <input style="margin-top:15px;position: relative;bottom:-1px;" type="checkbox" ${cookie['rememberMe'].value!=""&&cookie['rememberMe'].value!=null?"checked":""} ${cookie['rememberMe'].value!=""?"name='rememberMe' ":""} value="rememberMe" onclick="remember();" id="rememberMe"> <label style="font-size:15px;margin-left: 1px" for="rememberMe">Nhớ mật khẩu</label>                      
                    </div>

                    <div class="auth-form__aside">
                        <div class="auth-form__help">
                            <a href="#" class="auth-form__help-link auth-form__help-forgot">
                                Quên mật khẩu
                            </a>
                            <span class="auth-form__help-separate"></span>
                            <a href="#" class="auth-form__help-link">
                                Cần trợ giúp?
                            </a>
                        </div>
                    </div>

                    <div class="auth-form__controls">
                        <button id="hideBlock2" class="btn btn--normal auth-form__controls-back" onclick="hideForm();">TRỞ LẠI</button>
                        <button type="submit" value="login" name="action" class="btn btn--primary">ĐĂNG NHẬP</button>
                    </div>
                </form>

                <div class="auth-form__socials">
                    <a href="#" class="auth-form__socials--facebook btn btn--size-s btn--width-icon">
                        <i class="auth-form__socials-icon fab fa-facebook-square"></i>
                        <span class="auth-form__social-title">
                            Kết nối với facebook
                        </span>
                    </a>
                    <a href="#" class="auth-form__socials--google btn btn--size-s btn--width-icon">
                        <i class="auth-form__socials-icon fab fa-google auth-form__socials-icon--google-color"></i>
                        <span class="auth-form__social-title">
                            Kết nối với google
                        </span>
                    </a>
                </div>
            </div>
        </div>
    </div> 
            <script type="text/javascript" src="<c:url value='/assets/js/process.js'/>"></script>
            <script>     
                
                document.getElementById("hideBlock1").addEventListener("click", function(event){
                    event.preventDefault();
                });
                document.getElementById("hideBlock2").addEventListener("click", function(event){
                    event.preventDefault();
                });
                if(document.getElementById("leftArrow")!==null){
                     document.getElementById("leftArrow").addEventListener("click", function(event){
                    event.preventDefault();
                });
                }
                if(document.getElementById("rightArrow")!==null){
                    document.getElementById("rightArrow").addEventListener("click", function(event){
                    event.preventDefault();
                });
                }
                
                function hideForm(){
                    var process = document.getElementsByClassName("modal");
                    var login = document.getElementsByClassName("form-login");
                    var register = document.getElementsByClassName("form-register");
                    process[0].className = "modal";                  
                    register[0].className = "auth-form form-register";                
                    login[0].className = "auth-form form-login";
                }
                $(window).click(function() {
                     var boxHistorySearch = document.getElementById("boxSearch");
                        boxHistorySearch.style.display = "none";
                  });

                  $('#boxSearch').click(function(event){
                    event.stopPropagation();
                  });
                  $('.header__search-input').click(function(event){
                    event.stopPropagation();
                  });
                function showHistorySearch(){
                    var boxHistorySearch = document.getElementById("boxSearch");
                    boxHistorySearch.style.display = "block";
                }

                function checkHtmlCart(){
                    var div = document.getElementById("noCart");
                        div.innerHTML = "";
                        div.className = "header__cart-list";
                        div.innerHTML = `<h4 class="header__cart-heading">Sản phẩm đã thêm</h4>
                                    <ul id="productsIntoCart" class="header__cart-list-item">
                                        
                                    </ul>
                                    <div class="header__cart-view-cart">
                                        <a href="cartControl${detail!=null?"?pid=":""}${detail!=null?detail.productId:""}" class="header__cart-btn btn btn--primary">Xem giỏ hàng</a>
                                    </div>
`; 
                }
                function checkHtmlCartDelete(){ 
                        $.ajax({
                        url: "/checkNoCartDelete",
                        type:"get",
                        data: {
                        },
                        success: function(check){ 
                            if(check.includes("true")){
                                var div = document.getElementById("noCart");
                                div.innerHTML = "";
                                div.className = "header__cart-list header__cart-list--no-cart";
                                div.innerHTML = `<img src="<c:url value='/assets/img/no-cart.png'/>" alt="" class="header__cart-no-cart-img">
                                        <span class="header__cart-list--no-cart-msg">Chưa có sản phẩm</span>`; 
                            }
                        },
                        error: function (xhr){
                           
                        }
                    });
                }
                function remember()
                        {
                            if (document.getElementById('rememberMe').checked) 
                            {
                                document.getElementById('rememberMe').setAttribute("name","rememberMe");
                            } else {
                               document.getElementById('rememberMe').removeAttribute("name");
                            }
                        }
                function loadMore(){
                    var amount = document.getElementsByClassName("product").length;
                    $.ajax({
                        url: "/Shopee/load",
                        type:"get",
                        data: {
                            exist : amount
                        },
                        success: function(data){
                            var row = document.getElementById("content");
                            row.innerHTML += data;
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function addToCart(productId){
                    var row1 = document.getElementById("productsIntoCart");
                    var quanlity = document.getElementById("quanlity").value;
                    if(row1!==null){
                        var html = row1.innerHTML;
                    }
                        
                    $.ajax({
                        url: "/addToCart",
                        type:"get",
                        data: {
                            productId : productId,
                            quanlity:quanlity
                        },
                        success: function(data){
                            var row = document.getElementById("productsIntoCart");
                            if(html!==undefined){
                                row.innerHTML = html;
                            }
                            
                            row.innerHTML += data;
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function deleteFromCart(productId){
                    $.ajax({
                        url: "/deleteFromCart",
                        type:"get",
                        data: {
                            productId : productId
                        },
                        success: function(data){
                            var row = document.getElementById("productsIntoCart");
                            if(data!==null&&row!==null){
                                 row.innerHTML = data;
                            }
                           
                        },
                        error: function (xhr){
                            // Do something to handle error
                            
                        }
                    });
                }
                function changeCookieCart(productId){
                    $.ajax({
                        url: "/changeCookieCart",
                        type:"get",
                        data: {
                            productId : productId
                        },
                        success: function(id){
                            document.cookie = "id" + "=" + id + ";" + 24*60*60 + ";path=/";
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function changeCookieCartDelete(productId){
                    $.ajax({
                        url: "/changeCookieCartDelete",
                        type:"get",
                        data: {
                            productId : productId
                        },
                        success: function(id){
                            document.cookie = "id" + "=" + id + ";" + 24*60*60 + ";path=/";
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function changeNumberCart(){
                    $.ajax({
                        url: "/changeNumberCart",
                        type:"get",
                        data: {
                        },
                        success: function(number){
                            var row = document.getElementById("numberCart");
                            row.style.display="block";
                            row.innerHTML = number;
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function changeNumberCartDelete(){
                    $.ajax({
                        url: "/changeNumberCartDelete",
                        type:"get",
                        data: {
                        },
                        success: function(number){
                            var row = document.getElementById("numberCart");
                            if(number.charAt(0) === "0"){
                                 row.style.display="none";
                            }else{
                                row.style.display="block";
                                row.innerHTML = number;
                            }
                            
                            
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function checkNoCart(){
                    $.ajax({
                        url: "/checkNoCart",
                        type:"get",
                        data: {
                        },
                        success: function(check){
                            var row = document.getElementById("noCart");
                            if(check==="true"){
                                if(!row.className.includes("header__cart-list--no-cart")){
                                row.className +="header__cart-list--no-cart";
                                }   
                            }
                            
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function checkNoCartDelete(){
                    $.ajax({
                        url: "/checkNoCart",
                        type:"get",
                        data: {
                        },
                        success: function(check){
                            var row = document.getElementById("noCart");
                            if(check==="true"){
                                if(!row.className.includes("header__cart-list--no-cart")){
                                row.className +="header__cart-list--no-cart";
                                }   
                            }
                            
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function searchByName(param){
                    var txtSearch = param.value;
                    $.ajax({
                        url: "/searchAjax",
                        type:"get",
                        data: {
                            txt : txtSearch
                        },
                        success: function(data){
                            var row = document.getElementById("content");
                            row.innerHTML = data;
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function paggingForSearchByName(param){
                    var txtSearch = param.value;
                    $.ajax({
                        url: "/searchAjaxPagging",
                        type:"get",
                        data: {
                            txt : txtSearch
                        },
                        success: function(data){
                            var row = document.getElementById("pagging-direction");
                            row.innerHTML = data;
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function paggingTopForSearchByName(param){
                    var txtSearch = param.value;
                    $.ajax({
                        url: "/searchAjaxPaggingTop",
                        type:"get",
                        data: {
                            txt : txtSearch
                        },
                        success: function(data){
                        	console.log("zo");
                        	if(data == ""){
                        		var showEmptyResultSearchByAjax = document.getElementById("resultSearch");
                        		console.log("zo1");
                        		showEmptyResultSearchByAjax.innerHTML = `<div><img src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg//assets/a60759ad1dabe909c46a817ecbf71878.png"><div>Không tìm thấy kết quả nào</div><div>Hãy thử sử dụng các từ khóa chung chung hơn</div></div>`;
                        	} else{
                        		console.log("zo2");
                        		var row = document.getElementById("pagging-direction-top");
                                row.innerHTML = data;
                        	}                    
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
                function changeLinkPaging(param){
                    var txtSearch = param.value;
                    $.ajax({
                        url: "/searchAjaxChangeLinkPaging",
                        type:"get",
                        data: {
                            txt : txtSearch
                        },
                        success: function(data){
                            var row = document.getElementById("linkPaging");
                            row.innerHTML = data;
                            console.log("okee");
                        },
                        error: function (xhr){
                            // Do something to handle error
                        }
                    });
                }
            </script>
        </footer>
