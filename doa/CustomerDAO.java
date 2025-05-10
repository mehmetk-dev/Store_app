package doa;

import connection.DBConnection;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final String saveScript = """
            INSERT INTO customer(name,email,password) VALUES(?,?,?);
            """;

    private final String findByIdScript = """
            SELECT * FROM customer WHERE id = ?;
            """;

    private final String findAllScript = """
            SELECT * FROM customer;
            """;

    private final String findByEmailScript = """
            SELECT * FROM customer WHERE email = ?;
            """;

    public void save(Customer customer){

        try {

            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveScript);

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

        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(findByIdScript);
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

    private List<Customer> findAll(){

        List<Customer> customerList = new ArrayList<>();

        try{
            Connection connection = DBConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(findAllScript);
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

    public boolean existByMail(String email) {

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement pr = connection.prepareStatement(findByEmailScript);
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
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(findByEmailScript);
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
