package Session;

import org.hibernate.Session;


public class HibernateSession implements AutoCloseable {

    private final Session session;

    public HibernateSession(Session session) {
        this.session = session;
    }

    public Session delegate() {
        return session;
    }

    @Override
    public void close() {
        session.close();
    }
}
