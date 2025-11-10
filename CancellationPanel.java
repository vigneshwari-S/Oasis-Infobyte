import javax.swing.*;
import java.awt.*;

public class CancellationPanel extends JPanel {
    public CancellationPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(237, 255, 250));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(600,300));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        JLabel title = new JLabel("Cancel Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0,120,80));

        JTextField ticketField = new JTextField();
        ticketField.setMaximumSize(new Dimension(350,36));

        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton backBtn = new JButton("Back to Reservation");
        styleButton(cancelBtn);
        styleOutline(backBtn);

        cancelBtn.addActionListener(e -> {
            String t = ticketField.getText().trim();
            if (t.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter ticket id.");
                return;
            }
            JOptionPane.showMessageDialog(this, "❌ Ticket cancelled successfully.\nTicket ID: " + t);
            ticketField.setText("");
            main.showPanel("Reservation");
        });

        backBtn.addActionListener(e -> main.showPanel("Reservation"));

        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(new JLabel("Ticket ID")); card.add(ticketField);
        card.add(Box.createVerticalStrut(14));
        card.add(cancelBtn);
        card.add(Box.createVerticalStrut(8));
        card.add(backBtn);

        return card;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(0, 196, 120));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(200, 40));
        b.setBorder(BorderFactory.createEmptyBorder(8,16,8,16));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleOutline(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(0,120,80));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setMaximumSize(new Dimension(200, 40));
        b.setBorder(BorderFactory.createLineBorder(new Color(0,196,120), 2));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
