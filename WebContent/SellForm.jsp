<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Books Store Application - Book Form</title>
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
        form {
            margin: 0 auto;
            max-width: 600px;
            background: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        form table {
            width: 100%;
        }
        form table caption {
            text-align: center;
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        form table th, form table td {
            padding: 10px;
            text-align: left;
        }
        form table th {
            width: 30%;
        }
        form table td input[type="text"], 
        form table td textarea {
            width: 80%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        form table td input[type="file"] {
            padding: 5px;
        }

		form table td textarea {
		    width: 80%; /* Reduced width */
		    height: 80px; /* Set a fixed height for consistency */
		}

        form table td input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        form table td input[type="submit"]:hover {
            background-color: #45a049;
        }
        form table td img {
            max-width: 100px;
            max-height: 100px;
            margin-top: 10px;
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
        <center>
            <c:if test="${not empty errorMessage}">
                <div style="color: red; font-weight: bold;">
                    ${errorMessage}
                </div>
            </c:if>
        </center>
        <div align="center">
            <c:choose>
                <c:when test="${agency != null}">
                    <form action="update" method="post">
                        <!-- Existing update form content -->
                </c:when>
                <c:otherwise>
                    <form action="PredictCategoryServlet" method="post">
                        <table border="1" cellpadding="5">
                            <caption>
                                <h2>Sell Item</h2>
                            </caption>
                            <tr>
                                <th>Item Name: </th>
                                <td>
                                    <input type="text" id="name" name="name" size="45" value="${param.name}" required/>
                                </td>
                            </tr>
                            <tr>
                                <th>Brand: </th>
                                <td>
                                    <input type="text" id="brand" name="brand" size="45" value="${param.brand}" required/>
                                </td>
                            </tr>
                            <tr>
                                <th>Features: </th>
                                <td>
                                    <textarea id="features" name="features" rows="4" cols="45" required>${param.features}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <button type="submit">Predict Category</button>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <c:if test="${not empty category}">
                        <form action="insert" method="post">
                            <input type="hidden" name="name" value="${param.name}" />
                            <input type="hidden" name="brand" value="${param.brand}" />
                            <input type="hidden" name="feature" value="${param.features}" />
                            <input type="hidden" name="username" value="${sessionScope.username}" />
                            <table border="1" cellpadding="5">
                                <caption>
                                    <h2>Sell Item</h2>
                                </caption>
                                <tr>
                                    <th>Item Name: </th>
                                    <td>
                                        <input type="text" name="name" size="45" value="${param.name}" readonly/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Brand: </th>
                                    <td>
                                        <input type="text" name="brand" size="45" value="${param.brand}" readonly/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Features: </th>
                                    <td>
                                        <textarea name="features" rows="4" cols="45" readonly>${param.features}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Predicted Category: </th>
                                    <td>
                                        <input type="text" name="category" size="45" value="${category}" readonly/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Condition: </th>
                                    <td>
                                        <select name="product_condition">
                                            <option value="New">New</option>
                                            <option value="Used">Used</option>
                                            <option value="Refurbished">Refurbished</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Image: </th>
                                    <td>
                                        <input type="file" name="filepath" size="45"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Description: </th>
                                    <td>
                                        <textarea name="description" rows="4" cols="45">${agency.description}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Price: </th>
                                    <td>
                                        <input type="text" name="price" size="5" value="${agency.price}" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>Location: </th>
                                    <td>
                                        <input type="text" name="location" size="5" value="${agency.location}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input type="submit" value="Save" />
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
	<div class="footer">
	    &copy; 2024 Mary Exchange Agency. All rights reserved.
	</div>
</body>
</html>