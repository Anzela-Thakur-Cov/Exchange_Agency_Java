package com.mycoventry.projects.exchangeagency;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AgencyDAO agencyDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        agencyDAO = new AgencyDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertAgency(request, response);
                    break;
                case "/delete":
                    deleteAgency(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAgency(request, response);
                    break;
                case "/list":
                    String category = request.getParameter("category");
                    if (category != null && !category.isEmpty()) {
                        listAgencyByCategory(request, response, category);
                    } else {
                        listAgency(request, response);
                    }
                    break;
                default:
                    listAgency(request, response);
                    break;
                    
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAgency(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Agency> listAgency = agencyDAO.listAllAgencies();
        request.setAttribute("listAgency", listAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listAgencyByCategory(HttpServletRequest request, HttpServletResponse response, String category)
            throws SQLException, ServletException, IOException {
        System.out.println("Selected Category in listAgencyByCategory: " + category); // Debug output
        
        List<Agency> listAgency = agencyDAO.listAgencyByCategory(category);
        request.setAttribute("listAgency", listAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SellForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agency existingAgency = agencyDAO.getAgency(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SellForm.jsp");
        request.setAttribute("agency", existingAgency);
        dispatcher.forward(request, response);
    }

    private void insertAgency(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String filepath = request.getParameter("filepath");
        float price = Float.parseFloat(request.getParameter("price"));
        String description = request.getParameter("description");
        String product_condition = request.getParameter("product_condition");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String feature = request.getParameter("feature");
        String brand = request.getParameter("brand");
        String username = request.getParameter("username");
      
        
        System.out.println("name: "+ name);
        System.out.println("filepath: "+ filepath);
        System.out.println("price: "+ price);
        System.out.println("description: "+ description);
        System.out.println("condition: "+ product_condition);
        System.out.println("category: "+ category);
        System.out.println("location: "+ location);
        System.out.println("feature: "+ feature);
        System.out.println("brand: "+ brand);
        System.out.println("username: "+ username);


       

        Agency newAgency = new Agency(name, price, description, product_condition, category, location, feature, brand, username, filepath );
        agencyDAO.insertAgency(newAgency);

        response.sendRedirect("list");
    }

    private void updateAgency(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String description = request.getParameter("description");
        String product_condition = request.getParameter("product_condition");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String feature = request.getParameter("feature");
        String brand = request.getParameter("brand");
        String username = request.getParameter("username");
        String filepath = request.getParameter("filepath");

        

        Agency agency = new Agency(id, name, price, description, product_condition, category, location, feature, brand, username, filepath);
        agencyDAO.updateAgency(agency);

        response.sendRedirect("list");
    }

    private void deleteAgency(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Agency agency = new Agency(id);
        agencyDAO.deleteAgency(agency);

        response.sendRedirect("list");
    }

    
    
  
}
