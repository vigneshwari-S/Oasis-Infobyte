import javax.swing.*;
import java.awt.*;

public class CancellationPanel extends JPanel {
    public CancellationPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));  // Light blue background
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 380));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2)
            ),
            BorderFactory.createEmptyBorder(35, 40, 35, 40)
        ));

        // Warning icon
        JLabel icon = new JLabel("⚠", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        icon.setForeground(new Color(255, 193, 7));

        JLabel title = new JLabel("Cancel Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Enter your ticket ID to cancel", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(108, 117, 125));

        JTextField ticketField = createStyledTextField();

        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton backBtn = new JButton("Back to Reservation");
        
        styleDangerButton(cancelBtn);
        styleSecondaryButton(backBtn);

        cancelBtn.addActionListener(e -> {
            String t = ticketField.getText().trim();
            
            if (t.isEmpty()) {
                showErrorDialog(this, "Please enter ticket ID.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to cancel this ticket?\nTicket ID: " + t,
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                showSuccessDialog(this, "Ticket cancelled successfully.\nTicket ID: " + t);
                ticketField.setText("");
                main.showPanel("Reservation");
            }
        });

        backBtn.addActionListener(e -> main.showPanel("Reservation"));

        card.add(icon);
        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));
        card.add(createFieldLabel("Ticket ID"));
        card.add(Box.createVerticalStrut(8));
        card.add(ticketField);
        card.add(Box.createVerticalStrut(25));
        card.add(cancelBtn);
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
        field.setMaximumSize(new Dimension(380, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 53, 69), 2),
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

    private void styleDangerButton(JButton b) {
        b.setBackground(new Color(220, 53, 69));  // Red background
        b.setForeground(Color.BLACK);  // BLACK TEXT - ALWAYS VISIBLE
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(240, 44));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(200, 35, 51)); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(220, 53, 69)); 
            }
        });
    }

    private void styleSecondaryButton(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(108, 117, 125));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(240, 38));
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
