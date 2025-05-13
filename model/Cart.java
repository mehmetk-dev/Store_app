package model;

import java.math.BigDecimal;

public class Cart {

    private Long id;
    private Customer customer;
    private BigDecimal totalAmount;

    public Cart(Customer customer,BigDecimal totalAmount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    public Cart(Long id,Long customerId) {
        this.id = id;
        this.setCustomer(new Customer(customerId));
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
