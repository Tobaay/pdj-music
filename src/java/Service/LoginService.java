package Service;

import Database.DAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.User;

@Named
@SessionScoped
public class LoginService implements Serializable {

    @Inject
    private DAO dao;

    public LoginService() {

    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public User login(String username, String password) {
        return dao.getUser(username, password);
    }

}
