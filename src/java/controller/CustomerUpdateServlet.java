package controller;

import dao.CustomerDAO;
import dao.impl.CustomerJdbcDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CustomerUpdateServlet", urlPatterns = {"/CustomerUpdateServlet"})
public class CustomerUpdateServlet extends HttpServlet {

    private final CustomerDAO customerDAO = new CustomerJdbcDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNoStr = request.getParameter("accountNo");
        String name         = request.getParameter("name");
        String address      = request.getParameter("address");
        String phone        = request.getParameter("phone");
        String unitsStr     = request.getParameter("units");

        try {
            int accountNo = Integer.parseInt(accountNoStr);
            int units     = Integer.parseInt(unitsStr);

            // Expecting Customer model to have this constructor + getters/setters
            Customer customer = new Customer(accountNo, name, address, phone, units);

            boolean ok = customerDAO.updateCustomer(customer);
            if (ok) {
                // Go back to customer list (adjust path if your JSP differs)
                response.sendRedirect("customer_list.jsp?msg=updated");
            } else {
                request.setAttribute("error", "Update failed (no rows affected).");
                request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", "Invalid data: " + ex.getMessage());
            request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
        }
    }
}
