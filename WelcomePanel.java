import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(237, 255, 250));

        JPanel card = createCard(main);
        add(card);
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(600,380));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(24,24,24,24)
        ));

        JLabel title = new JLabel("Welcome to Online Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0,120,80));

        JLabel subtitle = new JLabel("Fast. Simple. Elegant.", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(Color.DARK_GRAY);

        JButton signIn = new JButton("Sign In");
        JButton signUp = new JButton("Sign Up");
        styleButton(signIn);
        styleOutline(signUp);

        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);

        signIn.addActionListener(e -> main.showPanel("Login"));
        signUp.addActionListener(e -> main.showPanel("Signup"));

        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(18));
        card.add(Box.createVerticalStrut(10));
        card.add(signIn);
        card.add(Box.createVerticalStrut(10));
        card.add(signUp);

        return card;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(0, 196, 120));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(200, 44));
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
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(200, 44));
        b.setBorder(BorderFactory.createLineBorder(new Color(0,196,120), 2));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
