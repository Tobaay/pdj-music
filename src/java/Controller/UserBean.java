package Controller;

import Database.DAO;
import Session.SessionUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private DAO dao;

    private User user;

    public UserBean() {
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return user != null;

    }
     public void logout() {
        HttpSession session = SessionUtil.getSession();
        user = null;
        session.invalidate();
    }
}
