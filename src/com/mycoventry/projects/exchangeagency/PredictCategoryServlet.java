package com.mycoventry.projects.exchangeagency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PredictCategoryServlet extends HttpServlet {
    private ProductCategoryPredictor predictor;

    @Override
    public void init() throws ServletException {
        try {
            predictor = new ProductCategoryPredictor();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize ProductCategoryPredictor", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String features = request.getParameter("features");
        
        System.out.println("Received parameters:");
        System.out.println("Name: " + name);
        System.out.println("Brand: " + brand);
        System.out.println("Features: " + features);
        
        // Validate the parameters
        if (name == null || name.isEmpty() || 
            brand == null || brand.isEmpty() || 
            features == null || features.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        String result;
        try {
            result = predictor.predictCategory(name, brand, features);
        } catch (Exception e) {
            throw new ServletException("Prediction failed", e);
        }

        request.setAttribute("category", result);
        request.getRequestDispatcher("SellForm.jsp").forward(request, response);
    }
}
