package Controller;

import Session.SessionUtil;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.User;

@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String username;
    private String password;

    @Inject
    private UserBean userBean;

    public LoginBean() {
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
        User result = userBean.getLoginService().login(username, password);

        if (result != null) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", username);
            userBean.setUser(result);
        
            userBean.getMessageBean().sendFacesMessage(
                    "Willkommen " + result.getUserName(),
                    "Dies ist die Entwicklungsumgebung!",
                    FacesMessage.SEVERITY_INFO);

        } else {
         
          userBean.getMessageBean().sendFacesMessage(
                  "Invalid Login!", 
                  "Please Try Again!", 
                  FacesMessage.SEVERITY_WARN);
           
        }
        username = password = "";
        return "index";
    }
}
