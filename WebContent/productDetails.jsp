<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            padding: 20px;
            padding-top: 100px;
            margin-bottom: 70px;
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

        .product-details {
            margin-bottom: 30px;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .product-image {
            text-align: center;
            margin-bottom: 20px;
        }

        .product-image img {
            width: 100%;
            max-width: 600px;
            height: auto;
            border-radius: 10px;
        }

        .product-name {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
			border: 1px solid #ddd;
			border-radius: 5px;
			padding: 10px;
        }

        .overview {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .overview div {
            flex: 1;
            padding: 10px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 0 10px;
        }

        .overview div:first-child {
            margin-left: 0;
        }

        .overview div:last-child {
            margin-right: 0;
        }

        .details {
            padding: 20px;
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 5px;
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
		<div class="button-container">
			           <a href="list">Back to Home</a>
			       </div>
        <section class="product-details">
            <div class="product-image">
                <img src="data:image/jpg;base64,${base64Image}" alt="${productName}">
            </div>
            <div class="product-name">
                ${productName}
            </div>
            <div class="overview">
                <div>
                    <p class="price"> <strong>Price: </strong>$${productPrice}</p>
                </div>
                <div>
                    <p class="location"><strong>Location: </strong>${productLocation}</p>
                </div>
            </div>
            <div class="details">
                <p><strong>Description: </strong>${productDescription}</p>
                <p><strong>Feature: </strong>${productFeature}</p>
                <p><strong>Brand: </strong>${productBrand}</p>
                <p><strong>Condition: </strong>${productCondition}</p>
                <p><strong>Category: </strong>${productCategory}</p>
            </div>
        </section>
    </div>
    <div class="footer">
        &copy; 2024 Mary Exchange Agency. All rights reserved.
    </div>
</body>
</html>
