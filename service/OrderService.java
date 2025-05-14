package service;

import dao.OrderDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.*;
import model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private CartItemService cartItemService;
    private PaymentService paymentService;
    private CartService cartService;
    private OrderItemService orderItemService;
    private ProductService productService;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.cartItemService = new CartItemService();
        this.paymentService = new PaymentService();
        this.cartService = new CartService();
        this.orderItemService = new OrderItemService();
        this.productService = new ProductService();
    }

    public Order save(Customer customer, PaymentType paymentType) throws StoreException {

        List<CartItem> cartItems =  cartItemService.getByCustomer(customer);

        if (cartItems.isEmpty()){
            throw new StoreException(ExceptionMessagesConstants.ORDER_ITEM_IS_EMPTY);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal amount = cartItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            totalAmount = totalAmount.add(amount);
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        long orderId = orderDAO.save(order);
        order.setId(orderId);

        List<OrderItem> orderItems = new ArrayList<>();

        cartItems.forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(new Order(orderId));
            orderItem.setProduct(new Product(cartItem.getProduct().getId()));
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            orderItems.add(orderItem);
        });

        orderItemService.save(orderItems);

        paymentService.save(order,paymentType);
        cartService.clear(customer);

        cartItems.forEach(cartItem -> {

            Product product = new Product(cartItem.getProduct().getId() );
            productService.updateStock(product,cartItem.getQuantity());
        });

        System.out.println("Sipariş ve ödeme işlemi başarıyla yapıldı.");

        return order;
    }

    public List<Order> getAllByCustomer(Customer loginedCustomer) {
        return orderDAO.findAllByCustomerId(loginedCustomer.getId());
    }
}
