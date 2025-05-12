package dao;

import connection.DBConnection;
import dao.Constant.SqlScriptConstants;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO<Customer>{

    public void save(Customer customer){

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScriptConstants.CUSTOMER_SAVE)) {

            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer findById(long id){

        Customer customer = null;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID)){

            pr.setLong(1,id);

            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("createddate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updateddate").getTime()).toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> findAll(){

        List<Customer> customerList = new ArrayList<>();

        try(Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement()){

            ResultSet rs = st.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL);
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("createddate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updateddate").getTime()).toLocalDateTime());
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(long id) {

    }

    public boolean existByMail(String email) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_EMAIL)){

            pr.setString(1,email);
            ResultSet rs = pr.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer findByEmail(String email) {

        Customer customer = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_EMAIL);){

            pr.setString(1,email);
            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setCreatedDate(new Timestamp(rs.getDate("createddate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updateddate").getTime()).toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
