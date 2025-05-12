package service;

import connection.DBConnection;
import doa.Constant.SqlScriptConstants;
import doa.UserDAO;
import exceptions.ExceptionMessagesConstants;
import exceptions.StoreException;
import model.User;
import model.enums.Role;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void login(String username, String password) throws StoreException {

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
    }
}

