import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));  // Light blue background
        JPanel card = createCard(main);
        add(card);
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 450));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // Add subtle shadow effect
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2)
            ),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));

        // Icon/Logo area - Using a custom panel instead of emoji
        JPanel iconPanel = createIconPanel();
        iconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Welcome to Online Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Fast • Simple • Elegant", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(108, 117, 125));

        JButton signIn = new JButton("Sign In");
        JButton signUp = new JButton("Create Account");
        
        stylePrimaryButton(signIn);
        styleSecondaryButton(signUp);
        
        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        signIn.addActionListener(e -> main.showPanel("Login"));
        signUp.addActionListener(e -> main.showPanel("Signup"));

        card.add(iconPanel);
        card.add(Box.createVerticalStrut(15));
        card.add(title);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(35));
        card.add(signIn);
        card.add(Box.createVerticalStrut(12));
        card.add(signUp);
        
        return card;
    }

    private JPanel createIconPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Set color to teal
                g2d.setColor(new Color(0, 150, 136));
                
                // Draw airplane shape
                int centerX = 30;
                int centerY = 30;
                
                // Airplane body (main fuselage)
                int[] bodyX = {centerX, centerX + 5, centerX + 25, centerX + 25, centerX + 5, centerX};
                int[] bodyY = {centerY - 3, centerY - 3, centerY - 3, centerY + 3, centerY + 3, centerY + 3};
                g2d.fillPolygon(bodyX, bodyY, 6);
                
                // Wings
                int[] wingX = {centerX + 8, centerX + 15, centerX + 15, centerX + 8};
                int[] wingY = {centerY, centerY - 12, centerY - 10, centerY};
                g2d.fillPolygon(wingX, wingY, 4);
                
                int[] wing2X = {centerX + 8, centerX + 15, centerX + 15, centerX + 8};
                int[] wing2Y = {centerY, centerY + 12, centerY + 10, centerY};
                g2d.fillPolygon(wing2X, wing2Y, 4);
                
                // Tail
                int[] tailX = {centerX - 2, centerX + 3, centerX + 3};
                int[] tailY = {centerY, centerY - 8, centerY + 8};
                g2d.fillPolygon(tailX, tailY, 3);
            }
        };
        
        panel.setPreferredSize(new Dimension(60, 60));
        panel.setMaximumSize(new Dimension(60, 60));
        panel.setOpaque(false);
        
        return panel;
    }

    private void stylePrimaryButton(JButton b) {
        b.setBackground(new Color(0, 150, 136));  // Teal background
        b.setForeground(Color.BLACK);  // BLACK TEXT - ALWAYS VISIBLE
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(250, 48));
        b.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
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
        b.setForeground(new Color(0, 150, 136));  // Teal text
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(true);
        b.setMaximumSize(new Dimension(250, 48));
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 150, 136), 2),
            BorderFactory.createEmptyBorder(11, 19, 11, 19)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(240, 252, 251)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setBackground(Color.WHITE); 
            }
        });
    }
}
