package service;

import dao.CustomerDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Customer;
import util.PasswordUtil;

public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password) throws StoreException {

        boolean ifExist = customerDAO.existByMail(email);
        if (ifExist){
            throw new StoreException(ExceptionMessagesConstants.CUSTOMER_EMAIL_ALLREADY_EXIST);
        }
        Customer customer = new Customer(name,email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Kayıt başarılı");
    }

    public void login(String email, String password) throws StoreException {

        boolean ifExist = customerDAO.existByMail(email);
        if (!ifExist){
            throw new StoreException(ExceptionMessagesConstants.CUSTOMER_EMAIL_DOES_NOT_EXIST);
        }

        String hashedPassword = PasswordUtil.hash(password);

        Customer foundCustomer =customerDAO.findByEmail(email);

        if (foundCustomer != null){
            boolean passwordEquals = foundCustomer.getPassword().equals(hashedPassword);
            if (!passwordEquals){
                throw new StoreException(ExceptionMessagesConstants.CUSTOMER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH);
            }else{
                System.out.println("Kullanıcı başarıyla giriş yaptı.");
            }
        }
    }
}
