package CustomerManagerRESTfullWebservice.service;

import CustomerManagerRESTfullWebservice.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);
}
