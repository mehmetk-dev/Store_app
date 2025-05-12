package service;

import dao.CategoryDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Category;
import model.User;
import model.enums.Role;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = categoryDAO;
    }

    public void save(String name, User user ) throws StoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new StoreException(ExceptionMessagesConstants.USER_IS_NOT_ADMIN);
        }

        categoryDAO.save(new Category(name,user,user));
        System.out.println("Kategori oluşturuldu.");
    }
}
