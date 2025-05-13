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

    public void addToCard(Customer loginedCustomer, Product product, int quantity) {

        Cart cart = getByCustomerId(loginedCustomer.getId());

        if (cart == null){
            cart = new Cart();
            cart.setCustomer(loginedCustomer);
        }

        cartDAO.save(new Cart(loginedCustomer.getId(),product.getId(),quantity));

        cart.getItems().add(new CartItem(product));

        System.out.println("Ürün sepetinize eklendi.");
    }
}
