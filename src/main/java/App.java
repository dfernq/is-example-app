import model.DBConnection;
import model.dao.UserDAO;
import model.dao.impl.UserDAOImpl;
import model.entities.User;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl(DBConnection.getInstance().getConnection());

        userDAO.list().stream()
            .filter(user -> "john_doe".equals(user.getUsername()))
            .findFirst()
            .ifPresentOrElse(
                user -> System.out.println("User found: " + user.getUsername()),
                () -> userDAO.create(User.builder()
                        .username("john_doe")
                        .email("john@acme.com")
                        .password("password123")
                        .build()));

    }
}