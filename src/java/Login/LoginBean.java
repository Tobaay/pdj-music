package Login;

import Database.DAO;
import Session.SessionUtil;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


@Named(value="loginBean")
@RequestScoped
public class LoginBean {
    
    private String username;
    private String password;
    
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
        boolean result = dao.login(username, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", username);
 
            return "home";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }
 
    public String logout() {
      HttpSession session = SessionUtil.getSession();
      session.invalidate();
      return "login";
   }
}
