package dao;

import connection.DBConnection;
import dao.Constant.SqlScriptConstants;
import model.User;
import model.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements BaseDAO<User> {
    @Override
    public void save(User user) {

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.USER_SAVE)){

            pr.setString(1,user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getRole().name());
            pr.setBoolean(4,user.isActive());
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(long id) {

    }

    public User findByUserName(String username) {
        User user = null;

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.USER_FIND_BY_USERNAME)) {

            pr.setString(1,username);
            ResultSet rs = pr.executeQuery();
            user = new User();
            while(rs.next()){

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setActive(rs.getBoolean("active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public boolean existByUserName(String userName) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.USER_FIND_BY_USERNAME)){

            pr.setString(1,userName);
            ResultSet rs = pr.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
