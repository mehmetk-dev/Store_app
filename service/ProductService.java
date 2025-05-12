package service;

import dao.ProductDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Product;
import model.User;
import model.enums.Role;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void save(Product product, User user) throws StoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new StoreException(ExceptionMessagesConstants.USER_IS_NOT_ADMIN);
        }

        product.setCreatedUser(user);
        product.setUpdatedUser(user);

        productDAO.save(product);

        System.out.println("Ürün kaydedildi.");
    }
}
