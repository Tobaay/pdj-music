
package syntaxfreaks.pdj.music.Database;

import syntaxfreaks.pdj.music.model.User;


public interface DAO {
    public User login(String username, String password);
}
