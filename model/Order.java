package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order extends BaseModel {

    private Customer customer;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(long id) {
        this.setId(id);
    }

    public Order(Customer customer) {
        this.customer = customer;
        this.orderDate = LocalDateTime.now();
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
