package service;

import dao.CategoryDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Category;
import model.User;
import model.enums.Role;

import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAO();

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

    public List<Category> listAll() {

        return categoryDAO.findAll();
    }

    public void deleteById(long id) {

        categoryDAO.delete(id);
        System.out.println("Kategori silindi.");
    }

    public Category getById(long categoryId) throws StoreException {

        Category foundCategory = categoryDAO.findById(categoryId);

        if (foundCategory == null){
            throw new StoreException(ExceptionMessagesConstants.CATEGORY_NOT_FOUND);
        }
        System.out.println("Kategori bulundu: " + foundCategory);
        return foundCategory;
    }
}
