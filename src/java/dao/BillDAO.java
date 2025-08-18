package dao;

import model.Bill;
import java.util.List;

public interface BillDAO {
    boolean saveBill(Bill bill);
    Bill getBillById(int id);
    List<Bill> getBillsByCustomerId(int customerId);
    List<Bill> getAllBills();
}
