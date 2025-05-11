package doa;

import connection.DBConnection;
import doa.Constant.SqlScriptConstants;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderDAO implements BaseDAO<Order>{



    public void save(Order order) {

        try(Connection connection = DBConnection.getConnection()) {

            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.ORDER_SAVE);

            pr.setLong(1,order.getCustomer().getId());
            pr.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            pr.setBigDecimal(3,order.getTotalAmount());

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }
}
