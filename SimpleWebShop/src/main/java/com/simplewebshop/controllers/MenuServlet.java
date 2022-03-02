package com.simplewebshop.controllers;

import com.simplewebshop.models.Product;
import com.simplewebshop.models.ProductCatalogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "MenuServlet", urlPatterns = "")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String categoryProducts = request.getParameter("categoryProducts");
        String category = request.getParameter("selected-category");
        List<String> addToCartButtons = new ArrayList<>();
        for(Product temp : ProductCatalogUtils.getProductsByCategory(category))
        {
            addToCartButtons.add(request.getParameter(temp.getName()+"Button"));
        }
        String myCart = request.getParameter("myCart");

        if(categoryProducts != null)
        {
            handleCategoryProducts(request,response,category);
        }
        else for(String temp : addToCartButtons) {
            if(temp != null) handleButton(request,response,temp,category);
        }

        if(myCart != null) handleMyCart(request,response);

    }

    private void handleCategoryProducts(HttpServletRequest request, HttpServletResponse response,String category) throws ServletException, IOException {
        String error;

        if(category == null || category.isBlank()) {
            error = "You did not fill in the category!";
            request.setAttribute("error",error);
        } else {
            int found = 0;
            for(String cat : ProductCatalogUtils.getCategories()) {
                if(Objects.equals(cat, category)) found=found+1;
            }
            if(found == 0) {
                error = "You did not fill in a requested category!";
                request.setAttribute("error",error);
            }
            else {
                List<Product> products = ProductCatalogUtils.getProductsByCategory(category);
                request.setAttribute("products",products);
            }
        }
        System.out.println("/post");
        request.setAttribute("selectedCategory",category);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    private void handleButton(HttpServletRequest request, HttpServletResponse response, String temp, String category)
            throws ServletException, IOException {
        Product newCartProduct = new Product();
        newCartProduct.setCategory(category);
        newCartProduct.setName(temp.replace("Button",""));
        ProductCatalogUtils.addToCart(newCartProduct);
        request.setAttribute("message","Product "+newCartProduct.getName()+" was added to your cart!");

        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    private void handleMyCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("cartProducts",ProductCatalogUtils.viewCart());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
