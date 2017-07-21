package Controller;

import Session.SessionUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;

@Named
@SessionScoped
public class UserBean implements Serializable{
    
    private User user;

    public UserBean() {
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
    
    public String logout() {
      HttpSession session = SessionUtil.getSession();
      user = null;
      session.invalidate();
      return "index";
   }
}
