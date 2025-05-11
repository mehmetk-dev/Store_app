package doa;

import connection.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDOA {

    public static final String searchByNameScript = """
            SELECT * FROM product where name LIKE ?;
            """;


    public List<Product> searchByName(String name){

        List<Product> products = new ArrayList<>();

        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(searchByNameScript);
            pr.setString(1,"%" + name + "%");
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setCreatedDate(LocalDateTime.parse(rs.getString("createddate")));
                product.setUpdatedDate(LocalDateTime.parse(rs.getString("updateddate")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  products;
    }
}
