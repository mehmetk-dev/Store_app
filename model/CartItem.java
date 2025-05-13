package model;

public class CartItem {

    private Product product;

    public CartItem(Product product) {
        this.product = new Product();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
