package com.mycoventry.projects.exchangeagency;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AgencyDAO agencyDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        agencyDAO = new AgencyDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            listAgencyByUsername(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAgencyByUsername(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String username = "anzelathakur"; // Hardcoded username
        List<Agency> listAgency = agencyDAO.listAgencyByUsername(username);
        request.setAttribute("listAgency", listAgency);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Upload.jsp");
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
    
}
