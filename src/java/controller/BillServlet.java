package controller;

import model.Bill;
import service.BillingService;
import service.impl.BillingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BillingService billingService;

    @Override
    public void init() throws ServletException {
        billingService = new BillingServiceImpl(); // ✅ Use service layer
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int unitsConsumed = Integer.parseInt(request.getParameter("units"));

            // ✅ Delegate to BillingService
            Bill bill = billingService.generateBill(customerId, unitsConsumed);

            request.setAttribute("bill", bill);
            request.getRequestDispatcher("view_bill.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error generating bill: " + e.getMessage());
            request.getRequestDispatcher("generate_bill.jsp").forward(request, response);
        }
    }
}
