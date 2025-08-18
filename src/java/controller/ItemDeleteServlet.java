package controller;

import dao.ItemDAO;
import dao.impl.ItemJdbcDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ItemDeleteServlet")
public class ItemDeleteServlet extends HttpServlet {
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
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            boolean deleted = itemDAO.deleteItem(itemId);

            if (deleted) {
                request.setAttribute("successMessage", "✅ Item deleted successfully!");
            } else {
                request.setAttribute("errorMessage", "⚠ Failed to delete item.");
            }

            // Redirect back to item list
            request.getRequestDispatcher("item_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "⚠ Error deleting item: " + e.getMessage());
            request.getRequestDispatcher("item_list.jsp").forward(request, response);
        }
    }
}
