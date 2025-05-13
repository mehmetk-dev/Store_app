package dao;

import connection.DBConnection;
import constants.Constants;
import dao.constants.SqlScriptConstants;
import model.Category;
import model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements BaseDAO<Product>{


    public List<Product> searchByName(String name){

        List<Product> products = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PRODUCT_SEARCH_BY_NAME)){

            pr.setString(1,"%" + name + "%");
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategory(new Category(rs.getLong("category_id"),rs.getString("category_name")));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  products;
    }


    @Override
    public void save(Product product) {

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PRODUCT_SAVE)){

            pr.setString(1,product.getName());
            pr.setBigDecimal(2,product.getPrice());
            pr.setInt(3,product.getStock());
            pr.setLong(4,product.getCreatedUser().getId());
            pr.setLong(5,product.getUpdatedUser().getId());
            pr.setLong(6,product.getCategory().getId());
            pr.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll(int page) {

        List<Product> productList = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PRODUCT_FIND_ALL)){
            int size =  Constants.PAGE_SIZE;
            int offset = (page - 1) * size;
            pr.setInt(1,size);
            pr.setInt(2,offset);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){

                productList.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"),rs.getString("category_name"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PRODUCT_DELETE_BY_ID)){

            pr.setLong(1,id);
            pr.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findTotalPage() {

        try(Connection connection = DBConnection.getConnection();
           Statement st = connection.createStatement()){

            ResultSet rs = st.executeQuery(SqlScriptConstants.PRODUCT_TOTAL_PAGE_COUNT);

            while (rs.next()) {
                int totalRows = rs.getInt(1);
                return (int) Math.ceil((double) totalRows /Constants.PAGE_SIZE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Product> findAllByCategoryName(String categoryName) {

        List<Product> productList = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.PRODUCT_FIND_BY_CATEGORY_NAME)){
            pr.setString(1,categoryName);
            ResultSet rs = pr.executeQuery();

            while (rs.next()){

                productList.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"),rs.getString("category_name"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}
