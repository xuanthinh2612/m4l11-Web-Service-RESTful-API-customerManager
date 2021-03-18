package CustomerManagerRESTfullWebservice.controllers;

import CustomerManagerRESTfullWebservice.model.Customer;
import CustomerManagerRESTfullWebservice.service.ICustomerService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping()
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ModelAndView Customers() {
        List<Customer> customers = customerService.findAll();
        return new ModelAndView("index", "customers", customers);
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/getCustomers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder) {
        customerService.save(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }


    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customer1 = customerService.findById(id);
        if (customer1 == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        } else {
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
//            customer1.setId(id);
            customerService.save(customer1);
            return new ResponseEntity<Customer>(customer1, HttpStatus.OK);

        }

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer==null){
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        else {
            customerService.remove(id);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
    }
//    public void removeCustomer(@PathVariable("id") Long id) {
//        customerService.remove(id);
//    }

}
