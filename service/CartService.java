package service;

import dao.CartDAO;
import dao.CartItemDAO;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

public class CartService {

    private CartDAO cartDAO;
    private CartItemDAO cartItemDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
        this.cartItemDAO = new CartItemDAO();
    }

    public void addToCard(Customer loginedCustomer, Product product, int quantity) {

        Cart cart = cartDAO.findByCustomerId(loginedCustomer.getId());

        if (cart == null){
            cart = new Cart();
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        cartItemDAO.save(cartItem);

//        cartDAO.save(new Cart(loginedCustomer.getId(),product.getId(),quantity));
//
//        cart.getItems().add(new CartItem(product));

        System.out.println("Ürün sepetinize eklendi.");
    }

    public void clear(Customer loginedCustomer) {

        Cart cart = cartDAO.findByCustomerId(loginedCustomer.getId());

        int effectedRow = cartItemDAO.clear(cart.getId());

        System.out.println("Sepetinizden "+effectedRow +" adet ürün silindi.");
    }
}
