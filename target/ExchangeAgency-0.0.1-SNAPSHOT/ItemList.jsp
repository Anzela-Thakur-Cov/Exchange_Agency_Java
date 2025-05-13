<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>E-commerce Exchange Agency</title>
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
            margin: 0 10px 0 0;
            font-size: 16px;
        }
        .right a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
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
            padding-right: 40px; /* Adjusted padding-right */
        }
        .container {
            padding: 20px;
			padding-top: 70px;
			margin-botton: 70px;
        }
        .dropdown {
            margin-bottom: 20px;
        }
        .item-grid {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }
        .item-card {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
            width: 300px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-decoration: none; 
            color: inherit;
            display: flex;
            flex-direction: column; 
            transition: transform 0.2s ease-out;
        }
        .item-card:hover {
            transform: translateY(-5px);
        }
        .item-card img {
            width: 100%;
            height: auto;
            border-top-left-radius: 10px; 
            border-top-right-radius: 10px;
        }
        .item-card .info {
            padding: 15px;
            flex-grow: 1; 
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .item-card .info h3 {
            margin: 10px 0;
            font-size: 1.2em;
            white-space: nowrap; 
            overflow: hidden; 
            text-overflow: ellipsis;
        }
        .item-card .info p {
            margin: 5px 0;
            color: #555;
        }
        .item-card .actions {
            display: flex;
            justify-content: space-around;
            align-items: center;
            padding: 10px;
            background-color: #f2f2f2;
            border-top: 1px solid #ddd;
        }
        .item-card .actions a {
            color: #4CAF50;
            text-decoration: none;
            padding: 8px 16px;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .item-card .actions a:hover {
            background-color: #4CAF50;
            color: white;
        }
        .item-card .actions a.edit {
            border-color: #4CAF50;
            color: #4CAF50;
        }
        .item-card .actions a.edit:hover {
            background-color: #4CAF50;
            color: white;
        }
        .item-card .actions a.delete {
            border-color: #f44336;
            color: #f44336;
        }
        .item-card .actions a.delete:hover {
            background-color: #f44336;
            color: white;
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
        <div class="dropdown">
            <form action="AgencyServlet" method="get" id="categoryForm">
            <label for="categories">Select Category:</label>
            <select id="categories" name="category">
                <option value="electronics">Electronics</option>
                <option value="furniture">Furniture</option>
                <option value="books">Books</option>
                <option value="clothing">Clothing</option>
                <option value="vehicle">Vehicle</option>
                <option value="property">Property</option>
                <option value="bikes">Bikes</option>
            </select>
            <button type="submit">Filter</button>
            </form>
        </div>
        <div class="item-grid">
            <c:forEach var="agency" items="${listAgency}">
                <div class="item-card">
                    <a href="productDetails?id=<c:out value='${agency.id}' />">
                        <img src="data:image/jpg;base64,${agency.base64Image}" width="240" height="300"/>
                        <div class="info">
                            <h3><c:out value="${agency.name}" /></h3>
                            <p>Price: $<c:out value="${agency.price}" /></p>
                            <p>Description: <c:out value="${agency.description}" /></p>
                        </div>
                        <div class="actions">
                            <a href="edit?id=<c:out value='${agency.id}' />" class="edit">Editing</a>
                            <a href="delete?id=<c:out value='${agency.id}' />" class="delete">Delete</a>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="footer">
        &copy; 2024 Mary Exchange Agency. All rights reserved.
    </div>
</body>
</html>
