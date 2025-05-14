package dao;

import connection.DBConnection;
import dao.constants.SqlScriptConstants;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category>{
    @Override
    public long save(Category category) {

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE)){

            pr.setString(1,category.getName());
            pr.setLong(2,category.getCreatedUser().getId());
            pr.setLong(3,category.getUpdatedUser().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Category findById(long id) {

        Category category = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_BY_ID)){

            category = new Category();
            pr.setLong(1,id);

            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                category.setName(rs.getString("name"));
                category.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll(int page) {

        List<Category> categoryList =  new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_ALL)){

            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                categoryList.add(new Category(rs.getLong("id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
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
