package model;

import dao.CartDAO;

public class CartService {

    private CartDAO cartDAO;

    public CartService() {
        cartDAO =  new CartDAO();
    }

    public Cart getByCustomerId(Long id){
        return cartDAO.findByCustomerId(id);
    }
}
