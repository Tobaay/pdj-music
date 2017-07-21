
package Database;

import model.User;

public interface DAO {
    public User login(String username, String password);
}
