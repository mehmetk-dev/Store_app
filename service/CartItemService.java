package service;

import dao.CartItemDAO;
import model.CartItem;
import model.Customer;

import java.util.List;

public class CartItemService {

    private CartItemDAO cartItemDAO;

    public CartItemService() {
        cartItemDAO =  new CartItemDAO();
    }

    public List<CartItem> getByCustomer(Customer customer) {
        return cartItemDAO.findByCustomerId(customer.getId());
    }
}
