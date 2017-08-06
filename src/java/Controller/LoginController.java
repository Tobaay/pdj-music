package Controller;

import Service.LoginService;
import Session.SessionUtil;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

@Named
@RequestScoped
@NoArgsConstructor
@Getter
@Setter
public class LoginController {

    private String username;
    private String password;

    @Inject
    private UserController userController;

    @Inject
    private LoginService loginService;
    
    @Inject
    private MessageController messageController;
    
    @Inject
    private SessionUtil sessionUtil;

    public String login() {
        User result = loginService.login(username, password);

        if (result != null) {
            HttpSession session = sessionUtil.getSession();
            session.setAttribute("username", username);
            userController.setUser(result);

            messageController.sendFacesMessage(
                    "Willkommen " + result.getUserName(),
                    "Dies ist die Entwicklungsumgebung!",
                    FacesMessage.SEVERITY_INFO);

        } else {

            messageController.sendFacesMessage(
                    "Invalid Login!",
                    "Please Try Again!",
                    FacesMessage.SEVERITY_WARN);

        }
        username = password = "";
        return "index";
    }
}
