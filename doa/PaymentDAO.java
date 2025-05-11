package doa;

import connection.DBConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {

    private static final String saveScript = """
            INSERT INTO payment(amount,order_id,payment_method) VALUES(?,?,?);
            """;

    public void save(Payment payment) {

        try(Connection connection = DBConnection.getConnection()){

            PreparedStatement pr = connection.prepareStatement(saveScript);
            pr.setBigDecimal(1,payment.getAmount());
            pr.setLong(2,payment.getOrder().getId());
            pr.setString(3,payment.getPaymentType().name());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
