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

public class RegistrationService implements Serializable{
    
    @Inject
    private DAO dao;

    public User searchUser(String username) {
        return dao.getUser(username);
    }
    
    public void setUser(String username, String password, String email){
        dao.setUser(username, password, email);
    }
    
}
