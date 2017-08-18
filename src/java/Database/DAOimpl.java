package Database;

import Controller.LoginController;
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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

            return result.size() == 1 ? result.get(0) : null;
        }
    }
    
    @Override
    public User getUser(String username) {
        
        try (HibernateSession session = new HibernateSession(NewHibernateUtil.getSessionFactory().openSession())){
            
            String sql = "from User where user_username = :username";
            org.hibernate.Query query = session.delegate().createQuery(sql);
            query.setParameter("username", username);
            
            List<User> result = query.list();
            
            return result.size() == 1 ? result.get(0) : null;
        }
    }
    
    @Override
    public void setUser(String username, String password, String email) {
        
        try (HibernateSession session = new HibernateSession(NewHibernateUtil.getSessionFactory().openSession())){
            
            session.delegate().beginTransaction();
            User queryUser = new User(username, password, email);
            session.delegate().persist(queryUser);
            session.delegate().getTransaction().commit();

        }
    }
}
