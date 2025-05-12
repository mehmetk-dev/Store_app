package dao;

import connection.DBConnection;
import dao.Constant.SqlScriptConstants;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category>{
    @Override
    public void save(Category category) {

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE)){

            pr.setString(1,category.getName());
            pr.setLong(2,category.getCreatedUser().getId());
            pr.setLong(3,category.getUpdatedUser().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(long id) {

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_BY_ID)){

            pr.setString(1,category.getName());
            pr.setLong(2,category.getCreatedUser().getId());
            pr.setLong(3,category.getUpdatedUser().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {

        try(Connection connection =DBConnection.getConnection();
        PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_DELETE_BY_ID)){

            pr.setLong(1,id);
            pr.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
