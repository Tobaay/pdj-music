package Controller;

import Service.LoginService;
import Session.SessionUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;
import Util.NewHibernateUtil;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private LoginService loginService;

    @Inject
    private MessageBean messageBean;

    private User user;

    public UserBean() {
    }

    @PostConstruct
    public static void init() {
        try {
            Class.forName("Util.NewHibernateUtil");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
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
