package service;

import doa.CustomerDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Customer;
import util.PasswordUtil;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password){

        boolean ifExist = customerDAO.existByMail(email);
        if (ifExist){
            throw new StoreException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALLREADY_EXIST);
        }
        Customer customer = new Customer(name,email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Kayıt başarılı");
    }
}
