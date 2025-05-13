package service;

import dao.OrderDAO;
import model.CartItem;
import model.Customer;
import model.Order;
import model.Product;
import model.enums.PaymentType;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private CartItemService cartItemService;
    private PaymentService paymentService;
    private CartService cartService;

    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.cartItemService = new CartItemService();
        this.paymentService = new PaymentService();
        this.cartService = new CartService();
    }

    public Order save(Customer customer, PaymentType paymentType){

        List<CartItem> cartItems =  cartItemService.getByCustomer(customer);

        BigDecimal totalAmount = BigDecimal.ZERO;

        cartItems.forEach(
                cartItem ->{
                        BigDecimal amount  = new BigDecimal(cartItem.getProduct().getPrice().intValue() * cartItem.getQuantity());
                        totalAmount.add(amount);
                }
        );

        List<Product> productList = cartItems.stream()
                .map(CartItem::getProduct).toList();


        Order order = new Order();
        order.setProducts(productList);
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);

        orderDAO.save(order);
        paymentService.save(order,paymentType);
        cartService.clear(customer);

        System.out.println("Sipariş ve ödeme işlemi başarıyla yapıldı.");

        return order;
    }
}
