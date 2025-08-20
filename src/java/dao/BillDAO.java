package dao;

import model.Bill;
import java.util.List;

public interface BillDAO {
    /** Insert and return generated bill_id (or -1 on failure). */
    int saveBill(Bill bill);

    List<Bill> getAllBills();
}
