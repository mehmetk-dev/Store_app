package service;

import dao.OrderItemDAO;
import model.OrderItem;

import java.util.List;

public class OrderItemService {

    private OrderItemDAO orderItemDAO;

    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAO();
    }

    public void save(List<OrderItem> orderItems){
        orderItemDAO.saveAll(orderItems);
        System.out.println("Sipariş ürünleri kaydedildi.");
    }
}
