package controller;

import model.Bill;
import service.BillingService;
import service.impl.BillingServiceImpl;
import dao.impl.CustomerJdbcDAO;
import dao.impl.BillJdbcDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {

    private BillingService billingService;
    private CustomerJdbcDAO customerDAO;
    private BillJdbcDAO billDAO;

    @Override
    public void init() throws ServletException {
        billingService = new BillingServiceImpl();
        customerDAO = new CustomerJdbcDAO();
        billDAO = new BillJdbcDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get parameters from form
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int units = Integer.parseInt(request.getParameter("units"));

            // Fetch customer
            Customer customer = customerDAO.getCustomerById(customerId);
            if (customer == null) {
                request.setAttribute("errorMessage", "Customer not found!");
                request.getRequestDispatcher("generateBill.jsp").forward(request, response);
                return;
            }

            // Generate bill
            Bill bill = billingService.generateBill(customer, units);

            // Save bill in DB
            boolean saved = billDAO.saveBill(bill);

            if (saved) {
                request.setAttribute("bill", bill);
                request.getRequestDispatcher("viewBill.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Bill could not be saved.");
                request.getRequestDispatcher("generateBill.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input or system error.");
            request.getRequestDispatcher("generateBill.jsp").forward(request, response);
        }
    }
}
