package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Order;
import model.OrderItem;
import model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements BaseDAO<Order>{

    public long save(Order order) {

        long generatedId = 0;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.ORDER_SAVE,Statement.RETURN_GENERATED_KEYS)) {

            pr.setLong(1,order.getCustomer().getId());
            pr.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            pr.setBigDecimal(3,order.getTotalAmount());

            pr.executeUpdate();

            ResultSet rs = pr.getGeneratedKeys();

            if (rs.next()){
                generatedId = rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }


    public List<Order> findAllByCustomerId(long customerId) {

        List<Order> orders = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.ORDER_FIND_BY_CUSTOMER_ID)){

            pr.setLong(1,customerId);

            ResultSet rs = pr.executeQuery();

            while(rs.next()){

                long orderId = rs.getLong("order_id");

                Order order = new Order();
                order.setId(orderId);
                //order.setOrderDate(LocalDateTime.parse(rs.getTimestamp("order_date").toString()));
                order.setOrderItems(new ArrayList<>());

                Product product = new Product();
                product.setId(rs.getLong("product_id"));
                product.setName(rs.getString("product_name"));

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getBigDecimal("price"));

                order.getOrderItems().add(orderItem);

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
