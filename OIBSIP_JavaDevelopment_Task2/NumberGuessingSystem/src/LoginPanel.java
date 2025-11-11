import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 450));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel title = new JLabel("Welcome Back!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112));

        JLabel subtitle = new JLabel("Sign in to continue playing", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(100, 100, 100));

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField userField = createStyledTextField();

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passField = createStyledPasswordField();

        JButton signInBtn = new JButton("Sign In");
        JButton backBtn = new JButton("Back to Home");

        stylePrimaryButton(signInBtn, new Color(100, 149, 237));
        styleSecondaryButton(backBtn);

        signInBtn.addActionListener(e -> {
            String u = userField.getText().trim();
            String p = new String(passField.getPassword());

            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (main.loginUser(u, p)) {
                JOptionPane.showMessageDialog(this, "Welcome back, " + u + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                main.showPanel("Game");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> main.showPanel("Welcome"));

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(30));
        card.add(userLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(userField);
        card.add(Box.createVerticalStrut(20));
        card.add(passLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(passField);
        card.add(Box.createVerticalStrut(30));
        card.add(signInBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(backBtn);

        return card;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(350, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        });

        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(350, 45));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        });

        return field;
    }

    private void stylePrimaryButton(JButton b, Color bg) {
        b.setBackground(bg);
        b.setForeground(Color.BLACK);  // BLACK TEXT
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(260, 50));
        b.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(bg);
            }
        });
    }

    private void styleSecondaryButton(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(100, 100, 100));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(260, 40));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setForeground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setForeground(new Color(100, 100, 100));
            }
        });
    }
}
