package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDAO implements BaseDAO<OrderItem> {

    public void saveAll(List<OrderItem> orderItems) {

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.ORDER_ITEMS_SAVE)){

            for (OrderItem orderItem : orderItems) {
                ps.setLong(1, orderItem.getOrder().getId());
                ps.setLong(2,orderItem.getProduct().getId());
                ps.setInt(3,orderItem.getQuantity());
                ps.setBigDecimal(4,orderItem.getPrice());
                ps.addBatch();
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long save(OrderItem orderItem) {
        return 0;
    }

    @Override
    public OrderItem findById(long id) {
        return null;
    }

    @Override
    public List<OrderItem> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(OrderItem orderItem) {

    }

    @Override
    public void delete(long id) {

    }
}
