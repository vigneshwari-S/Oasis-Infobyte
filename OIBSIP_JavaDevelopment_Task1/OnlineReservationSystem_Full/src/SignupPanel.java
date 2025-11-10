import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {
    public SignupPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));  // Light blue background
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 520));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2)
            ),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Join us today and start booking", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(108, 117, 125));

        JTextField nameField = createStyledTextField();
        JTextField mobileField = createStyledTextField();
        JPasswordField passField = createStyledPasswordField();
        JPasswordField confirmField = createStyledPasswordField();

        JButton createBtn = new JButton("Create Account");
        JButton backBtn = new JButton("Already have an account? Sign In");
        
        stylePrimaryButton(createBtn);
        styleSecondaryButton(backBtn);

        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String mobile = mobileField.getText().trim();
            String p = new String(passField.getPassword());
            String cp = new String(confirmField.getPassword());

            if (name.isEmpty() || mobile.isEmpty() || p.isEmpty()) {
                showErrorDialog(this, "Please fill all fields.");
                return;
            }

            if (!p.equals(cp)) {
                showErrorDialog(this, "Passwords do not match.");
                return;
            }

            main.registerUser(name, mobile, p);
            showSuccessDialog(this, "Account created successfully!");
            main.showPanel("Reservation");
        });

        backBtn.addActionListener(e -> main.showPanel("Welcome"));

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));
        card.add(createFieldLabel("Full Name"));
        card.add(Box.createVerticalStrut(6));
        card.add(nameField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("Mobile Number"));
        card.add(Box.createVerticalStrut(6));
        card.add(mobileField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));
        card.add(passField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("Confirm Password"));
        card.add(Box.createVerticalStrut(6));
        card.add(confirmField);
        card.add(Box.createVerticalStrut(20));
        card.add(createBtn);
        card.add(Box.createVerticalStrut(10));
        card.add(backBtn);

        return card;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(52, 58, 64));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(350, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 150, 136), 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(350, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 150, 136), 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        
        return field;
    }

    private void stylePrimaryButton(JButton b) {
        // ⚠️ CRITICAL: BLACK TEXT ON TEAL BACKGROUND
        b.setBackground(new Color(0, 150, 136));  // Teal color
        b.setForeground(Color.BLACK);  // ⭐ BLACK TEXT - ALWAYS VISIBLE ⭐
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(230, 44));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(0, 121, 107)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(0, 150, 136)); 
            }
        });
    }

    private void styleSecondaryButton(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(108, 117, 125));  // Gray text
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(280, 36));
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setForeground(new Color(0, 150, 136)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setForeground(new Color(108, 117, 125)); 
            }
        });
    }

    private void showErrorDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
