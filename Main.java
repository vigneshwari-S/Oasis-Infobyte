import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    CardLayout cardLayout;
    JPanel cards;
    Map<String, User> users = new HashMap<>(); // mobile -> User
    User currentUser = null;

    public Main() {
        setTitle("Online Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top navigation
        TopNavPanel topNav = new TopNavPanel(this);
        add(topNav, BorderLayout.NORTH);

        // Cards
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new WelcomePanel(this), "Welcome");
        cards.add(new SignupPanel(this), "Signup");
        cards.add(new LoginPanel(this), "Login");
        cards.add(new ReservationPanel(this), "Reservation");
        cards.add(new CancellationPanel(this), "Cancellation");

        add(cards, BorderLayout.CENTER);
        showPanel("Welcome");
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(cards, name);
    }

    public void registerUser(String name, String mobile, String password) {
        users.put(mobile, new User(name, mobile, password));
        currentUser = users.get(mobile);
    }

    public boolean loginUser(String mobile, String password) {
        User u = users.get(mobile);
        if (u != null && u.password.equals(password)) {
            currentUser = u;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
        showPanel("Welcome");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
