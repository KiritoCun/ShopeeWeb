<%-- 
    Document   : Edit
    Created on : Sep 22, 2021, 4:29:06 PM
    Author     : TechCare
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit product</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link id="favico" rel="icon" type="image/png" href="https://bizweb.dktcdn.net/100/327/577/files/2.png"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value='/assets/css/manager.css'/>" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Add <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="doAddProduct" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <a href="manageProduct?index=${tag}">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </a>

                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Description</label>
                                    <input name="description" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>URL Image</label>
                                    <input  name="url_image" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price old</label>
                                    <input  name="price_old" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price current</label>
                                    <input  name="price_current" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Province</label>
                                    <input  name="province" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Sale off</label>
                                    <input  name="sale_off" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listC}" var="o">
                                            <option ${1 == o.categoryId ?"selected":""} value="${o.categoryId}">${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>