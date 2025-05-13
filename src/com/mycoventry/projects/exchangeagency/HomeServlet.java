package com.mycoventry.projects.exchangeagency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AgencyDAO agencyDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        agencyDAO = new AgencyDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void listAgency(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Agency> listAgency = agencyDAO.listAllAgencies();
  //      System.out.println("Number of agencies retrieved: " + listAgency.size()); // Debugging line
        request.setAttribute("listAgency", listAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
    }


    private void listAgencyByCategory(HttpServletRequest request, HttpServletResponse response, String category) throws SQLException, ServletException, IOException {
        List<Agency> listAgency = agencyDAO.listAgencyByCategory(category);
        request.setAttribute("listAgency", listAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
    }
    


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agency existingAgency = agencyDAO.getAgency(id);
        request.setAttribute("agency", existingAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAgency(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
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

        Agency newAgency = new Agency(name, price, description, product_condition, category, location, feature, brand, username, filepath);
        agencyDAO.insertAgency(newAgency);

        response.sendRedirect("list");
    }

    private void updateAgency(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
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

    private void deleteAgency(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Agency agency = new Agency(id);
        agencyDAO.deleteAgency(agency);

        response.sendRedirect("list");
    }
    

}
