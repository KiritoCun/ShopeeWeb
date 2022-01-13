<%-- 
    Document   : ManagerProduct
    Created on : Sep 20, 2021, 6:01:42 PM
    Author     : TechCare
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Manage Product</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link id="favico" rel="icon" type="image/png"
	href="https://bizweb.dktcdn.net/100/327/577/files/2.png" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="<c:url value='/assets/css/manager.css'/>" rel="stylesheet"
	type="text/css" />
<style>
img {
	width: 200px;
	height: 120px;
}
/* Set a style for all buttons */
button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

/* Float cancel and delete buttons and add an equal width */
.cancelbtn, .deletebtn {
	float: left;
	width: 50%;
}

/* Add a color to the cancel button */
.cancelbtn {
	background-color: #ccc;
	color: black;
}

/* Add a color to the delete button */
.deletebtn {
	background-color: #f44336;
}

/* Add padding and center-align text to the box */
.box {
	padding: 16px;
	text-align: center;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0,0,0,0.4);
	padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 40%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* The Modal Close Button (x) */
.close {
	position: absolute;
	right: 35px;
	top: 15px;
	font-size: 40px;
	font-weight: bold;
	color: #f1f1f1;
}

.close:hover, .close:focus {
	color: #f44336;
	cursor: pointer;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}
</style>
<body>
	<div class="box">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>
							Manage <b>Product</b>
						</h2>
					</div>
					<div class="col-sm-6">
						<a href="${pageContext.request.contextPath}/addProduct" class="btn btn-success" data-toggle="modal"><i
							class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
						<a href="" class="btn btn-danger" data-toggle="modal"><i
							class="material-icons">&#xE15C;</i> <span>Delete</span></a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><span class="custom-checkbox"> <input
								type="checkbox" id="selectAll"> <label for="selectAll"></label>
						</span></th>
						<th>ID</th>
						<th>Name</th>
						<th>Image</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listP}" var="o">
						<tr>
							<td><span class="custom-checkbox"> <input
									type="checkbox" id="checkbox1" name="options[]" value="1">
									<label for="checkbox1"></label>
							</span></td>
							<td>${o.productId}</td>
							<td>${o.description}</td>
							<td><img style="width: 130px;" src="${o.url}"></td>
							<td style="width: 110px;">${o.priceCurrent/1000}00đ</td>
							<td><a href="loadProduct?pid=${o.productId}&tag=${tag}"
								class="edit" data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a style="cursor:pointer" onclick="document.getElementById('id01').style.display='block';confirmDeleteProduct(${o.productId})"
								 class="delete"
								data-toggle="modal"><i class="material-icons"
									data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix">
				<div class="hint-text">
					Showing <b>3</b> out of <b>${total}</b> entries
				</div>
				<ul class="pagination">
					<li class="${tag==1?"invisible":""} page-item"><a
						href="manageProduct?index=${tag-1}">Previous</a></li>
					<c:forEach begin="1" end="${endPage}" var="i">
						<li class="${i==tag?"active":""} page-item"><a
							href="manageProduct?index=${i}" class="page-link">${i}</a></li>
					</c:forEach>
					<li class="${tag==endPage?"invisible":""} page-item"><a
						href="manageProduct?index=${tag+1}" class="page-link">Next</a></li>
				</ul>
			</div>
		</div>
		<a href="home"><button type="button" class="btn btn-primary">Back
				to home</button></a>

	</div>

	<script src="<c:url value='assets/js/manager.js'/>"
		type="text/javascript"></script>
	<div id="id01" class="modal">
		<span onclick="document.getElementById('id01').style.display='none'"
			class="close" title="Close Modal">×</span>
		<form class="modal-content" action="/action_page.php">
			<div class="box">
				<h1>Delete Product</h1>
				<p>Are you sure you want to delete your product?</p>

				<div class="clearfix">
					<button type="button"
						onclick="document.getElementById('id01').style.display='none'"
						class="cancelbtn">Cancel</button>
					<button id="confirmDeleteProduct" type="button"
						class="deletebtn"><a>Delete</a></button>
				</div>
			</div>
		</form>
	</div>

	<script>
		// Get the modal
		var modal = document.getElementById('id01');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
		
		function confirmDeleteProduct(productId){
            $.ajax({
                url: "/changeUrlDeleteProduct",
                type:"get",
                data: {
                    txt : productId
                },
                success: function(data){
                    var row = document.getElementById("confirmDeleteProduct");
                    row.innerHTML = data;
                },
                error: function (xhr){
                    // Do something to handle error
                }
            });
        }
	</script>
</body>
</html>