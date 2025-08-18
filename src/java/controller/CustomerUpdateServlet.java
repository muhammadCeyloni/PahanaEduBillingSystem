package controller;

import dao.CustomerDAO;
import dao.impl.CustomerJdbcDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerJdbcDAO(); // use DAO implementation
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int accountNo = Integer.parseInt(request.getParameter("accountNo"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            int units = Integer.parseInt(request.getParameter("units"));

            Customer customer = new Customer(accountNo, name, address, phone, units);

            boolean updated = customerDAO.updateCustomer(customer);

            if (updated) {
                request.setAttribute("successMessage", "✅ Customer updated successfully!");
                request.getRequestDispatcher("customer_list.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "⚠ Failed to update customer.");
                request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error updating customer: " + e.getMessage());
            request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
        }
    }
}
