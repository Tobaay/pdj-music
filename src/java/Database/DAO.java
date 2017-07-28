
package Database;

import model.User;

public interface DAO {
    public User getUser(String username, String password);
}
