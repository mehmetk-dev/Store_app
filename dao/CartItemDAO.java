package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Cart;
import model.CartItem;
import model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {

    public int clear(Long cartId){

        int effectedRow = 0;
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CART_ITEM_DELETE)){

            pr.setLong(1,cartId);
            effectedRow = pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  effectedRow;
    }

    public List<CartItem> findByCustomerId(Long customerId){

        List<CartItem> cartItems = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CART_ITEM_FIND_BY_CUSTOMER_ID)){

            pr.setLong(1,customerId);

            ResultSet rs = pr.executeQuery();

            while(rs.next()){

                long cartItemId = rs.getLong("cart_item_id");
                int quantity = rs.getInt("quantity");
                long productId = rs.getLong("product_id");
                String productName = rs.getString("product_name");
                BigDecimal price = rs.getBigDecimal("price");

                Product product = new Product(productId,productName,price);
                cartItems.add(new CartItem(cartItemId,product, new Cart(),quantity));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void save(CartItem cartItem) {

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr =  connection.prepareStatement(SqlScriptConstants.CART_ITEM_SAVE)){

            pr.setLong(1,cartItem.getCart().getId());
            pr.setLong(2,cartItem.getProduct().getId());
            pr.setInt(3,cartItem.getQuantity());
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public List<Cart> findAllByCustomerId(long customerId) {

        List<Cart> carts = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CART_FIND_ALL_BY_CUSTOMER_ID)){

            pr.setLong(1,customerId);

            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                Cart cart = new Cart();

                cart.setItems(List.of(new CartItem(new Product(rs.getString("product_name")))));
                int quantity = rs.getInt("quantity");
                cart.setQuantity(quantity);
                BigDecimal price = rs.getBigDecimal("price");
                cart.setTotalAmount(new BigDecimal(price.intValue() * quantity));
                carts.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }*/
}
