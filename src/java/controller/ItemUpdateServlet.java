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
        itemDAO = new ItemJdbcDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Ensure your item_edit.jsp uses these exact names:
            // itemId, title, author (optional), price, quantity
            String itemIdStr = request.getParameter("itemId");
            String title = request.getParameter("title");
            String author = request.getParameter("author"); // may be null/empty
            String priceStr = request.getParameter("price");
            String qtyStr = request.getParameter("quantity");

            if (itemIdStr == null || title == null || priceStr == null || qtyStr == null
                    || title.trim().isEmpty() || priceStr.trim().isEmpty() || qtyStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Missing required fields");
            }

            int itemId = Integer.parseInt(itemIdStr.trim());
            double price = Double.parseDouble(priceStr.trim());
            int quantity = Integer.parseInt(qtyStr.trim());

            Item item = new Item(itemId, title.trim(), author != null ? author.trim() : null, price, quantity);

            boolean updated = itemDAO.updateItem(item);

            if (updated) {
                request.setAttribute("successMessage", "✅ Item updated successfully!");
                request.getRequestDispatcher("item_list.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "⚠ Failed to update item (no rows affected).");
                request.getRequestDispatcher("item_edit.jsp").forward(request, response);
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Invalid number format for price/quantity/itemId.");
            request.getRequestDispatcher("item_edit.jsp").forward(request, response);

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            request.setAttribute("errorMessage", "⚠ " + iae.getMessage());
            request.getRequestDispatcher("item_edit.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error updating item: " + e.getMessage());
            request.getRequestDispatcher("item_edit.jsp").forward(request, response);
        }
    }
}
