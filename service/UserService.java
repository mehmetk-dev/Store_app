package service;

import doa.UserDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.User;
import util.PasswordUtil;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void save(String username, String password) throws StoreException {

        User foundUser = userDAO.findByUserName(username);

        if (foundUser != null) {
            throw new StoreException(ExceptionMessagesConstants.USER_EMAIL_ALLREADY_EXIST);
        }

        userDAO.save(new User(username,PasswordUtil.hash(password)));
    }

    public User login(String username, String password) throws StoreException {

        User foundUser = userDAO.findByUserName(username);

        if (foundUser != null){
            String hashedPassword = PasswordUtil.hash(password);
            if (!foundUser.getPassword().equals(hashedPassword)){
                throw new StoreException(ExceptionMessagesConstants.USER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH);
            }
        } else{
            throw new StoreException(ExceptionMessagesConstants.USER_PASSWORD_OR_EMAIL_DOES_NOT_MATCH);
        }
        return foundUser;
    }
}
