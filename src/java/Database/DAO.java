
package Database;

import model.User;

public interface DAO {
    public User getUser(String username, String password);
    public User getUser(String username);
    public void setUser(String username, String password, String email);
}
