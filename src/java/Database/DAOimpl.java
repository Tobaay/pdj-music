package Database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class DAOimpl implements DAO{

    //TODO replace MOCK!
    @Override
    public boolean login(String username, String password) {
        return "admin".equals(username) && "1234".equals(password) ? true : false;
    }
    
}
