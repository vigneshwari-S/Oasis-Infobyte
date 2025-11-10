import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(237, 255, 250));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(700,340));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(24,24,24,24)
        ));

        JLabel title = new JLabel("Sign In With Mobile Number", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0,120,80));

        JTextField mobileField = new JTextField();
        JPasswordField passField = new JPasswordField();
        Dimension d = new Dimension(360,38);
        mobileField.setMaximumSize(d);
        passField.setMaximumSize(d);

        // inputs panel to give better spacing
        JPanel inputs = new JPanel();
        inputs.setOpaque(false);
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(new JLabel("Phone Number"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(mobileField);
        inputs.add(Box.createVerticalStrut(12));
        inputs.add(new JLabel("Password"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(passField);

        // button panel with centered buttons of equal size
        JButton signInBtn = new JButton("Sign In");
        JButton backBtn = new JButton("Back");
        stylePrimary(signInBtn);
        styleOutline(backBtn);

        Dimension btnSize = new Dimension(200,44);
        signInBtn.setPreferredSize(btnSize);
        signInBtn.setMaximumSize(btnSize);
        backBtn.setPreferredSize(btnSize);
        backBtn.setMaximumSize(btnSize);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(signInBtn);
        buttonRow.add(backBtn);

        // actions
        signInBtn.addActionListener(e -> {
            String m = mobileField.getText().trim();
            String p = new String(passField.getPassword());
            if (m.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            if (main.loginUser(m, p)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                main.showPanel("Reservation");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });
        backBtn.addActionListener(e -> main.showPanel("Welcome"));

        // assemble card
        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(inputs);
        card.add(Box.createVerticalStrut(18));
        card.add(buttonRow);

        return card;
    }

    private void stylePrimary(JButton b) {
        b.setBackground(new Color(0, 196, 120));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8,16,8,16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(new Color(0,170,100)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(new Color(0,196,120)); }
        });
    }

    private void styleOutline(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(0,120,80));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(0,196,120), 2));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(new Color(235, 255, 245)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(Color.WHITE); }
        });
    }
}
