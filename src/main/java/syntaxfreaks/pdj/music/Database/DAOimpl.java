package syntaxfreaks.pdj.music.Database;

import java.util.List;
import javax.enterprise.context.Dependent;
import org.hibernate.Session;
import syntaxfreaks.pdj.music.Util.NewHibernateUtil;
import syntaxfreaks.pdj.music.model.User;

@Dependent
public class DAOimpl implements DAO {

    @Override
    public User login(String username, String password) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        List<User> result = null;
        try {
            String sql = "from User";
            org.hibernate.Query query = s.createQuery(sql);
            result = query.list();

            for (User user : result) {
                if (user.getUserName().equals(username)
                        && user.getUserPassword().equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return null;
    }
}
