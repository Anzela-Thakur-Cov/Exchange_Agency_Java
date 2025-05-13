<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Interested Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #0078D4;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
        .right {
            display: flex;
            align-items: center;
        }
        .right h2 {
            margin: 0 40px 0 0;
            font-size: 16px;
        }
        .right a {
            color: white;
            text-decoration: none;
            margin: 0 40px;
            font-size: 16px;
        }
        .right a:hover {
            text-decoration: underline;
        }
        .company-name {
            font-size: 16px;
            padding-top: 20px;
            padding-bottom: 20px;
            font-size: 20px;
            padding-right: 40px;
        }
        .container {
            padding: 20px;
            padding-top: 100px;
            margin-bottom: 70px;
        }
        .cart-title {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .cart-item {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin-bottom: 20px;
            padding: 20px;
            display: flex;
            align-items: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .cart-item img {
            width: 100px;
            height: auto;
            border-radius: 8px;
            margin-right: 20px;
        }
        .item-details {
            flex-grow: 1;
        }
        .item-details h3 {
            margin: 0 0 10px 0;
            font-size: 18px;
        }
        .item-details p {
            margin: 5px 0;
            color: #555;
        }
        .item-action {
            text-align: right;
        }
        .item-action button {
            background-color: #0078D4;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .item-action button:hover {
            background-color: #005a9e;
        }
        .footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .sell-button {
            background-color: #d40020;
            color: white;
            border: 2px solid #0078D4;
            border-radius: 5px;
            padding: 10px 20px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }
        .sell-button:hover {
            color: #0078D4;
            background-color: white;
        }
		.button-container {
		            display: flex;
		            justify-content: space-between;
		            align-items: center;
		            margin-bottom: 20px;
		        }
		        .button-container a,
		        .button-container button {
		            padding: 10px 20px;
		            text-decoration: none;
		            border: none;
		            border-radius: 5px;
		            background-color: #0078D4;
		            color: white;
		            cursor: pointer;
		            font-size: 16px;
		        }
		        .button-container button {
		            background-color: #f44336;
		        }
    </style>
</head>
<body>
    <div class="header">
        <div class="company-name">Mary Exchange Agency</div>
        <div class="right">
            <h2>Hello, <c:out value="${sessionScope.username}" />!</h2>
            <a href="SignInServlet">Login</a>
            <a href="InterestedItemsServlet">Interested</a>
            <a href="upload">Uploads</a>
            <a href="SellForm.jsp" class="sell-button">+ Sell</a>
        </div>
    </div>
    <div class="container">
        <div class="cart-title">Interested Items</div>
		<div class="button-container">
		           <a href="list">Back to Home</a>
		        
		       </div>
        <c:forEach var="item" items="${interestedItems}">
            <div class="cart-item">
                <img src="data:image/jpeg;base64,${item.base64Image}" alt="Image">
                <div class="item-details">
                    <h3><c:out value="${item.name}" /></h3>
                    <p>Price: $<c:out value="${item.price}" /></p>
                    <p>Brand: <c:out value="${item.brand}" /></p>
                </div>
                <div class="item-action">
                    <form action="InterestedItemsServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}" />
                        <input type="hidden" name="username" value="${item.username}" />
                        <button type="submit" onclick="return confirm('Are you sure you want to delete this item?');">Remove</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="footer">
        &copy; 2024 Mary Exchange Agency. All rights reserved.
    </div>
</body>
</html>
