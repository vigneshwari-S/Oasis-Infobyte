import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));  // Light blue background
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 400));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2)
            ),
            BorderFactory.createEmptyBorder(35, 40, 35, 40)
        ));

        JLabel title = new JLabel("Sign In to Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Enter your credentials to continue", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(108, 117, 125));

        // Labels for fields
        JLabel mobileLabel = createFieldLabel("Phone Number");
        JLabel passLabel = createFieldLabel("Password");

        JTextField mobileField = createStyledTextField();
        JPasswordField passField = createStyledPasswordField();

        JButton signInBtn = new JButton("Sign In");
        JButton backBtn = new JButton("Back to Home");
        
        stylePrimaryButton(signInBtn);
        styleSecondaryButton(backBtn);

        signInBtn.addActionListener(e -> {
            String m = mobileField.getText().trim();
            String p = new String(passField.getPassword());
            
            if (m.isEmpty() || p.isEmpty()) {
                showErrorDialog(this, "Please fill all fields.");
                return;
            }

            if (main.loginUser(m, p)) {
                showSuccessDialog(this, "Login successful!");
                main.showPanel("Reservation");
            } else {
                showErrorDialog(this, "Invalid credentials.");
            }
        });

        backBtn.addActionListener(e -> main.showPanel("Welcome"));

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));
        card.add(mobileLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(mobileField);
        card.add(Box.createVerticalStrut(16));
        card.add(passLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(passField);
        card.add(Box.createVerticalStrut(25));
        card.add(signInBtn);
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
        
        // Focus effect - border turns teal when clicked
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
        
        // Focus effect - border turns teal when clicked
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
        b.setBackground(new Color(0, 150, 136));  // Teal color
        b.setForeground(Color.BLACK);  // BLACK TEXT - ALWAYS VISIBLE
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(230, 44));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Hover effect - darker teal on hover
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
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(230, 38));
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Hover effect - text turns teal on hover
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
