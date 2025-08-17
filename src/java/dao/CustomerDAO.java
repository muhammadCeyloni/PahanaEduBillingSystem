package dao;
import model.Customer;
import java.util.List;
public interface CustomerDAO {
  void save(Customer c) throws Exception;
  void update(Customer c) throws Exception;
  void delete(int id) throws Exception;
  Customer findById(int id) throws Exception;
  List<Customer> findAll() throws Exception;
}
