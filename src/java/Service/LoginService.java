package Service;

import Database.DAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

@Named
@SessionScoped
@NoArgsConstructor
@Getter
@Setter
public class LoginService implements Serializable {

    @Inject
    private DAO dao;

    public User login(String username, String password) {
        return dao.getUser(username, password);
    }

}
