package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {

    public void clear(Long customerId){

    }

    public Cart findByCustomerId(Long customerId){

        Cart cart = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID)){

            pr.setLong(1,customerId);

            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                cart = new Cart(new Customer(rs.getLong("customer_id")),
                        List.of(new CartItem(new Product(rs.getLong("product_id")))),
                        BigDecimal.valueOf(123L));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
