import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopNavPanel extends JPanel {
    public TopNavPanel(Main main) {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 255, 250));
        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JLabel brand = new JLabel("Online Reservation");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 18));
        brand.setForeground(new Color(0, 120, 80));

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navButtons.setOpaque(false);

        JButton homeBtn = navButton("Home");
        JButton reserveBtn = navButton("Reservation");
        JButton cancelBtn = navButton("Cancel");
        JButton logoutBtn = navButton("Logout");

        homeBtn.addActionListener(e -> main.showPanel("Welcome"));
        reserveBtn.addActionListener(e -> main.showPanel("Reservation"));
        cancelBtn.addActionListener(e -> main.showPanel("Cancellation"));
        logoutBtn.addActionListener(e -> { main.logout(); });

        navButtons.add(homeBtn);
        navButtons.add(reserveBtn);
        navButtons.add(cancelBtn);
        navButtons.add(logoutBtn);

        add(brand, BorderLayout.WEST);
        add(navButtons, BorderLayout.EAST);
    }

    private JButton navButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(0, 196, 120));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(new Color(0,170,100)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(new Color(0,196,120)); }
        });
        return b;
    }
}
