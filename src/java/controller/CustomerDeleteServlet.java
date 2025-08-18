package controller;

import dao.CustomerDAO;
import dao.impl.CustomerJdbcDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerJdbcDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int accountNo = Integer.parseInt(request.getParameter("accountNo"));
            boolean deleted = customerDAO.deleteCustomer(accountNo);

            if (deleted) {
                request.setAttribute("successMessage", "✅ Customer deleted successfully!");
            } else {
                request.setAttribute("errorMessage", "⚠ Failed to delete customer.");
            }

            // Redirect back to customer list
            request.getRequestDispatcher("customer_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error deleting customer: " + e.getMessage());
            request.getRequestDispatcher("customer_list.jsp").forward(request, response);
        }
    }
}
