package controller;

import dao.ItemDAO;
import dao.impl.ItemJdbcDAO;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ItemUpdateServlet")
public class ItemUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;

    @Override
    public void init() throws ServletException {
        itemDAO = new ItemJdbcDAO(); // use DAO implementation
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Item item = new Item(itemId, name, price, quantity);

            boolean updated = itemDAO.updateItem(item);

            if (updated) {
                request.setAttribute("successMessage", "✅ Item updated successfully!");
                request.getRequestDispatcher("item_list.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "⚠ Failed to update item.");
                request.getRequestDispatcher("item_edit.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error updating item: " + e.getMessage());
            request.getRequestDispatcher("item_edit.jsp").forward(request, response);
        }
    }
}
