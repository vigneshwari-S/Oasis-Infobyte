import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main extends JFrame {
    CardLayout cardLayout;
    JPanel cards;
    Map<String, String> users = new HashMap<>(); // username -> password
    String currentUser = null;
    File usersFile;
    File scoresFile;

    public Main() {
        setTitle("🎯 Number Guessing Game");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // System look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        usersFile = new File("users.txt");
        scoresFile = new File("scores.txt");

        try {
            if (!usersFile.exists()) usersFile.createNewFile();
            if (!scoresFile.exists()) scoresFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadUsers();

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(new Color(245, 247, 250));

        cards.add(new WelcomePanel(this), "Welcome");
        cards.add(new SignupPanel(this), "Signup");
        cards.add(new LoginPanel(this), "Login");
        cards.add(new GamePanel(this), "Game");
        cards.add(new ScoreboardPanel(this), "Scoreboard");

        add(cards, BorderLayout.CENTER);
        showPanel("Welcome");
        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(cards, name);
    }

    public void registerUser(String username, String password) {
        users.put(username, password);
        currentUser = username;
        saveUsers();
    }

    public boolean loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
        showPanel("Welcome");
    }

    public void saveScore(int score) {
        try (FileWriter fw = new FileWriter(scoresFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(currentUser + "," + score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> loadAggregateScores() {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(scoresFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String user = parts[0];
                    int sc = Integer.parseInt(parts[1]);
                    map.put(user, map.getOrDefault(user, 0) + sc);
                }
            }
        } catch (Exception e) {
        }
        return map;
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (Exception e) {
        }
    }

    private void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter(usersFile))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                out.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
