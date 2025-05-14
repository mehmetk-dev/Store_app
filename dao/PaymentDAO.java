package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment>{



    public long save(Payment payment) {

        long generatedId = 0;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE)){

            pr.setBigDecimal(1,payment.getAmount());
            pr.setLong(2,payment.getOrder().getId());
            pr.setString(3,payment.getPaymentType().name());
            pr.executeUpdate();

            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()){
                generatedId = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    @Override
    public Payment findById(long id) {
        return null;
    }

    @Override
    public List<Payment> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(long id) {

    }


}
