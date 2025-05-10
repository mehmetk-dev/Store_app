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
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }
}
