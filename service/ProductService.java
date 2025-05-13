package service;

import dao.ProductDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.Product;
import model.User;
import model.enums.Role;

import java.util.List;

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

    public List<Product> listAll(int page){
        return productDAO.findAll(page);
    }

    public void deleteByID(long id) {

        productDAO.delete(id);
        System.out.println("Ürün başarıyla silindi.");
    }

    public int getTotalPage() {

        return productDAO.findTotalPage();
    }

    public List<Product> searchProduct(String name) {

        return productDAO.searchByName(name);
    }

    public List<Product> filterProductByCategoryName(String searchCategory) {
        return productDAO.findAllByCategoryName(searchCategory);
    }

    public Product getByName(String productName) {
        return productDAO.findByName(productName);
    }
}
