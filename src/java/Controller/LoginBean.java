package Controller;

import Database.DAO;
import Session.SessionUtil;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;


@Named(value="loginBean")
@RequestScoped
public class LoginBean {
    
    private String username;
    private String password;
    
    @Inject
  private UserBean userBean;
    
    @Inject 
    private DAO dao;
    
    
    public LoginBean(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        User result = dao.login(username, password);
        
        if (result != null) {
            // get Http Session and store username
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", username);
            userBean.setUser(result);
 
            return "home";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "template";
        }
    }
 
    public String logout() {
      HttpSession session = SessionUtil.getSession();
      userBean.setUser(null);
      session.invalidate();
      return "template";
   }
}
