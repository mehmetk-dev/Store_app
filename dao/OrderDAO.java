package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Order;

import java.sql.*;
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
}
