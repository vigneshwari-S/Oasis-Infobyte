import javax.swing.*;
import java.awt.*;

public class TopNavPanel extends JPanel {
    public TopNavPanel(Main main) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 150, 136));  // Teal background
        setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        // Brand/Logo section
        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        brandPanel.setOpaque(false);
        
        JLabel icon = new JLabel("✈ ");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        icon.setForeground(Color.WHITE);
        
        JLabel brand = new JLabel("Online Reservation");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 19));
        brand.setForeground(Color.WHITE);
        
        brandPanel.add(icon);
        brandPanel.add(brand);

        // Navigation buttons
        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        navButtons.setOpaque(false);

        JButton homeBtn = navButton("Home");
        JButton reserveBtn = navButton("Reservation");
        JButton cancelBtn = navButton("Cancel");
        JButton logoutBtn = navButton("Logout");

        homeBtn.addActionListener(e -> main.showPanel("Welcome"));
        reserveBtn.addActionListener(e -> main.showPanel("Reservation"));
        cancelBtn.addActionListener(e -> main.showPanel("Cancellation"));
        logoutBtn.addActionListener(e -> { 
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                main.logout(); 
            }
        });

        navButtons.add(homeBtn);
        navButtons.add(reserveBtn);
        navButtons.add(cancelBtn);
        navButtons.add(logoutBtn);

        add(brandPanel, BorderLayout.WEST);
        add(navButtons, BorderLayout.EAST);
    }

    private JButton navButton(String text) {
        JButton b = new JButton(text);
        
        // WHITE BUTTON WITH DARK TEXT - MAXIMUM CONTRAST
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(33, 37, 41));  // Dark gray/black text
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(true);  // Fill the button background
        
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        
        // Hover effect - light gray background
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(240, 240, 240));  // Light gray
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setBackground(Color.WHITE);  // Back to white
            }
        });
        
        return b;
    }
}
