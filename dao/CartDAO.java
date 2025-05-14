package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO implements BaseDAO<Cart> {
    @Override
    public long save(Cart cart) {
        return 0;
    }

    @Override
    public Cart findById(long id) {
        return null;
    }

    @Override
    public List<Cart> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void delete(long id) {

    }

    public Cart findByCustomerId(long customerId) {
        Cart cart = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID)){

            pr.setLong(1,customerId);

            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                cart = new Cart(rs.getLong("id")
                        ,rs.getLong("customer_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }
}
