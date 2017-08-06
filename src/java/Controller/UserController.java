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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

@Named
@SessionScoped
@NoArgsConstructor
@Getter
@Setter
public class UserController implements Serializable {

    @Inject
    private MessageController messageController;

    @Inject
    private SessionUtil sessionUtil;

    private User user;

    @PostConstruct
    public static void init() {
        try {
            Class.forName("Util.NewHibernateUtil");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isLoggedIn() {
        return user != null;

    }

    public void logout() {
        HttpSession session = sessionUtil.getSession();
        user = null;
        session.invalidate();
    }
}
