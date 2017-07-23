package Controller;

import Database.DAO;
import Session.SessionUtil;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
        User result = userBean.getDao().login(username, password);
        FacesContext context = FacesContext.getCurrentInstance();

        if (result != null) {
            // get Http Session and store username
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", username);
            userBean.setUser(result);

            context.addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Willkommen " + result.getUserName(),
                            "Dies ist die Entwicklungsumgebung!"));

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
        }
        username = password = "";
        //RequestContext.getCurrentInstance().addCallbackParam("loggedIn", result != null);
        return "index";
    }
}
