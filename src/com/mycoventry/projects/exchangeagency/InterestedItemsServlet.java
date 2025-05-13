package com.mycoventry.projects.exchangeagency;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InterestedItemsServlet")
public class InterestedItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InterestedItem> interestedItems = new ArrayList<>();
        
        String query = "SELECT i.id, i.username, i.interested_date, a.name, a.price, a.brand, a.filepath, a.image "
                     + "FROM interesteditem i "
                     + "JOIN agency a ON i.id = a.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                java.sql.Timestamp interestedDate = rs.getTimestamp("interested_date");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String brand = rs.getString("brand");
                String filepath = rs.getString("filepath");
                Blob blob = rs.getBlob("image");
                
                byte[] imageBytes = null;
                String base64Image = null;

                if (blob != null) {
                    try (InputStream inputStream = blob.getBinaryStream();
                         ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        imageBytes = outputStream.toByteArray();
                        base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    } catch (IOException e) {
                        e.printStackTrace(); // Handle the exception appropriately
                    }
                }

                InterestedItem item = new InterestedItem(id, username, interestedDate, name, price, brand, base64Image, filepath);
                interestedItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error", e);
        }

        request.setAttribute("interestedItems", interestedItems);
        request.getRequestDispatcher("/interestedItems.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");

        if (id == null || username == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        String deleteQuery = "DELETE FROM interesteditem WHERE id = ? AND username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, username);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // Successfully deleted, redirect to the interested items page
                response.sendRedirect("InterestedItemsServlet");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
