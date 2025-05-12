package service;

import dao.OrderDAO;
import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {

    public OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = new OrderDAO();
    }

    public Order save(Customer customer, List<Product> productList){

        BigDecimal totalAmount = productList.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setProducts(productList);
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);

        orderDAO.save(order);

        return order;
    }
}
