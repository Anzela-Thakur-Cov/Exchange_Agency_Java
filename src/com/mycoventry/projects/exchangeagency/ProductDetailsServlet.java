package com.mycoventry.projects.exchangeagency;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // JDBC connection parameters
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    
    public void init() {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
            // SQL query to retrieve product details
            String sql = "SELECT * FROM agency WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            
            // Execute query
            rs = stmt.executeQuery();
            
            // Process result
            if (rs.next()) {
                // Retrieve product details from result set
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                String productDescription = rs.getString("description");
                String productImage = rs.getString("filepath");
                String productLocation = rs.getString("location");
                String productFeature = rs.getString("feature");
                String productUsername = rs.getString("username");
                String productBrand = rs.getString("brand");
                String productCondition = rs.getString("product_condition");
                String productCategory = rs.getString("category");
                
                // Handle image data
                Blob blob = rs.getBlob("image");
                String base64Image = null;
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                }
                
                // Set product details as request attributes
                request.setAttribute("productName", productName);
                request.setAttribute("productPrice", productPrice);
                request.setAttribute("productDescription", productDescription);
                request.setAttribute("productImage", productImage);
                request.setAttribute("productLocation", productLocation);
                request.setAttribute("productFeature", productFeature);
                request.setAttribute("productUsername", productUsername);
                request.setAttribute("productBrand", productBrand);
                request.setAttribute("productCondition", productCondition);
                request.setAttribute("productCategory", productCategory);
                request.setAttribute("base64Image", base64Image);
                
                // Forward to productDetails.jsp
                request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
            } else {
                // Product not found
                response.getWriter().println("Product not found");
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching product details", e);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
