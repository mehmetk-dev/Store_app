package doa;

import connection.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderDAO {

    public static final String saveScipt = """
            INSERT INTO \"order\"(customer_id,order_date,total_amount) VALUES(?,?,?);
            """;

    public void save(Order order) {

        try(Connection connection = DBConnection.getConnection()) {

            PreparedStatement pr = connection.prepareStatement(saveScipt);
            pr.setLong(1,order.getCustomer().getId());
            pr.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            pr.setBigDecimal(3,order.getTotalAmount());

            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
