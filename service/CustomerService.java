package service;

import doa.CustomerDAO;
import model.Customer;
import util.PasswordUtil;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password){

        Customer customer = new Customer(name,email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Kayıt başarılı");
    }
}
