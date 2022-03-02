<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.simplewebshop.models.ProductCatalogUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>
    @import url(https://fonts.googleapis.com/css?family=Raleway);
    body {
        font-family: 'Raleway', sans-serif;
        background: linear-gradient(rgba(255, 255, 255, .6), rgba(255, 255, 255, .6)),
        url("resources/images/bgimg2.jpg") no-repeat right bottom fixed;
        background-size: cover;
        scroll-behavior: smooth;
    }

    #title {
        border-radius: 20px;
        border: solid darkblue;
        text-align: center;
    }

    #sub-title {
        border-radius: 50px;
        border: solid darkblue;
        text-align: center;
        padding: 20px 20px 20px 20px;
    }

    footer {
        font-family: 'Raleway', sans-serif;
        text-align: left;
        background-color: black;
        color: white;
        max-width: 100%;
        height: 25px;
        bottom:0;
        left:0;

        position: absolute;
    }

    #layout {
        font-family: 'Raleway', sans-serif;
        display: flex;
        justify-content: space-evenly;
        flex-direction: column;
        align-items: stretch;
        gap:50px;
    }

    #box {
        font-family: 'Raleway', sans-serif;
        text-align: center;
    }

    #form {
        font-family: 'Raleway', sans-serif;
        text-align: center;
        display: flex;
        gap:10px;
        justify-content: center;
        align-items: center;
    }

    button {
        font-family: 'Raleway', sans-serif;
        border-radius : 20px;
        background-color: black;
        color : white;
        font-size: 15px;
        border : solid darkblue;
        box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
    }

    #label {
        color: saddlebrown;
        text-decoration: underline;
        text-decoration-color: darkmagenta;
    }
    #error{
        color:red;
    }

    form ul li {
        border: solid black;
    }

    #selected-category {
        border-radius: 5px;
        border: solid;
    }

    #categories {
        border-radius: 40px;
        border: solid darkgreen;
    }
</style>
<head>
    <title>Very Simple Web Shop</title>
</head>
<body>
    <div id="layout">
        <div id="title">
            <h1>Welcome to our Web Shop!</h1>
        </div>

        <div id="sub-title">
            <h2>Select a category to check all our products!</h2>
        </div>

        <div id="box">
            <select id="categories">
                <option id="initial">Insert one of these categories!</option>
                    <%
                        for(String temp : ProductCatalogUtils.getCategories() )
                        {
                    %>
                <option value="<%=temp%>"><%=temp%></option>
                    <%  } %>
            </select>
        </div>


            <form action="/" method="post">
                <div id="form">
                <p id="error">${error}</p>
                <label id="label" for="selected-category">Selected category:</label>
                <input id="selected-category" name="selected-category" value="${selectedCategory}">

                <button type="submit" name="categoryProducts" value="categoryProducts">Dive in!</button>

                <p>
                    <ul>
                        <c:forEach items="${products}" var="product">
                            <li>Product: ${product.name} <button type="submit" name="${product.name}Button" value="${product.name}Button">Add to cart</button></li>
                        </c:forEach>
                    </ul>
                </p>

                <p>${message}</p>
                <button type="submit" name="myCart" value="myCart">View Your Cart</button>
                <p>
                <ul>
                    <c:forEach items="${cartProducts}" var="product">
                        <li>Product: ${product.name}</li>
                    </c:forEach>
                </ul>
                </p>
                </div>
            </form>

</div>
<footer>
    © Copyright 2022 Andrei Mosor
</footer>
<br/>
</body>
</html>