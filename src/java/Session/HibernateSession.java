package Session;

import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class HibernateSession implements AutoCloseable {

    private final Session session;

    public Session delegate() {
        return session;
    }

    @Override
    public void close() {
        session.close();
    }
}
