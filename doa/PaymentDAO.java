package doa;

import connection.DBConnection;
import doa.Constant.SqlScriptConstants;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO implements BaseDAO{



    public void save(Payment payment) {

        try(Connection connection = DBConnection.getConnection()){

            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE);
            pr.setBigDecimal(1,payment.getAmount());
            pr.setLong(2,payment.getOrder().getId());
            pr.setString(3,payment.getPaymentType().name());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public Object findById(long id) {
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(long id) {

    }
}
