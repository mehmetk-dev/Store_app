package service;

import dao.UserDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.User;
import model.enums.Role;
import util.PasswordUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void save(String username, String password, Role role) throws StoreException {

        boolean isExist = userDAO.existByUserName(username);

        if (isExist) {
            throw new StoreException(ExceptionMessagesConstants.USER_EMAIL_ALLREADY_EXIST);
        }

        userDAO.save(new User(username,PasswordUtil.hash(password),role));
    }

    public User login(String username, String password) throws StoreException {

        User foundUser = userDAO.findByUserName(username);

        if (foundUser != null){
            String hashedPassword = PasswordUtil.hash(password);
            if (!hashedPassword.equals(foundUser.getPassword())){
                throw new StoreException(ExceptionMessagesConstants.USER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH);
            }
        } else{
            throw new StoreException(ExceptionMessagesConstants.USER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH);
        }
        System.out.println("Giriş başarılı " + foundUser.getUsername());
         
        return foundUser;
    }
}

