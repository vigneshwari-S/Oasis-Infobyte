import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {
    public SignupPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 550));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112));

        JLabel subtitle = new JLabel("Join the game and start playing!", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(100, 100, 100));

        JLabel userLabel = createFieldLabel("Username");
        JTextField userField = createStyledTextField();

        JLabel passLabel = createFieldLabel("Password");
        JPasswordField passField = createStyledPasswordField();

        JLabel confirmLabel = createFieldLabel("Confirm Password");
        JPasswordField confirmField = createStyledPasswordField();

        JButton createBtn = new JButton("Create Account");
        JButton backBtn = new JButton("Already have an account? Sign In");

        stylePrimaryButton(createBtn, new Color(34, 139, 34));
        styleSecondaryButton(backBtn);

        createBtn.addActionListener(e -> {
            String u = userField.getText().trim();
            String p = new String(passField.getPassword());
            String c = new String(confirmField.getPassword());

            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!p.equals(c)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            main.registerUser(u, p);
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            main.showPanel("Game");
        });

        backBtn.addActionListener(e -> main.showPanel("Login"));

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));
        card.add(userLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(userField);
        card.add(Box.createVerticalStrut(15));
        card.add(passLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(passField);
        card.add(Box.createVerticalStrut(15));
        card.add(confirmLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(confirmField);
        card.add(Box.createVerticalStrut(25));
        card.add(createBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(backBtn);

        return card;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(60, 60, 60));
        return label;
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
                    BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
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
                    BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
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
        b.setForeground(new Color(100, 149, 237));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(300, 38));
        b.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(new Color(240, 245, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(Color.WHITE);
            }
        });
    }
}
