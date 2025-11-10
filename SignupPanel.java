import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {
    public SignupPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(237, 255, 250));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(700,420));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(24,24,24,24)
        ));

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0,120,80));

        JTextField nameField = new JTextField();
        JTextField mobileField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        Dimension d = new Dimension(360,36);
        nameField.setMaximumSize(d);
        mobileField.setMaximumSize(d);
        passField.setMaximumSize(d);
        confirmField.setMaximumSize(d);

        JPanel inputs = new JPanel();
        inputs.setOpaque(false);
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(new JLabel("Full Name"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(nameField);
        inputs.add(Box.createVerticalStrut(10));
        inputs.add(new JLabel("Mobile Number"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(mobileField);
        inputs.add(Box.createVerticalStrut(10));
        inputs.add(new JLabel("Password"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(passField);
        inputs.add(Box.createVerticalStrut(10));
        inputs.add(new JLabel("Confirm Password"));
        inputs.add(Box.createVerticalStrut(6));
        inputs.add(confirmField);

        JButton createBtn = new JButton("Create Account");
        JButton backBtn = new JButton("Back");
        stylePrimary(createBtn);
        styleOutline(backBtn);

        Dimension btnSize = new Dimension(200,44);
        createBtn.setPreferredSize(btnSize);
        createBtn.setMaximumSize(btnSize);
        backBtn.setPreferredSize(btnSize);
        backBtn.setMaximumSize(btnSize);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(createBtn);
        buttonRow.add(backBtn);

        createBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String mobile = mobileField.getText().trim();
            String p = new String(passField.getPassword());
            String cp = new String(confirmField.getPassword());
            if (name.isEmpty() || mobile.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            if (!p.equals(cp)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }
            main.registerUser(name, mobile, p);
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            main.showPanel("Reservation");
        });

        backBtn.addActionListener(e -> main.showPanel("Welcome"));

        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(inputs);
        card.add(Box.createVerticalStrut(16));
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
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(new Color(235,255,245)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(Color.WHITE); }
        });
    }
}
