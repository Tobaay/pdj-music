package Database;

import Controller.LoginBean;
import Session.HibernateSession;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import model.User;
import Util.NewHibernateUtil;

@Dependent
public class DAOimpl implements DAO, Serializable {

    public DAOimpl() {
    }

    @PostConstruct
    public static void init() {
        try {
            Class.forName("Util.NewHibernateUtil");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUser(String username, String password) {
        
        try (HibernateSession session = new HibernateSession(NewHibernateUtil.getSessionFactory().openSession())){
            
            String sql = "from User where user_username = :username and user_password = :password";
            org.hibernate.Query query = session.delegate().createQuery(sql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            List<User> result = query.list();

            return result.get(0);
        }
        
    }
}
