import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ReservationPanel extends JPanel {
    public ReservationPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(650, 580));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 4, 4),
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2)
            ),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel title = new JLabel("Make a Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Book your journey with ease", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(108, 117, 125));

        JTextField nameField = createStyledTextField();
        JTextField fromField = createStyledTextField();
        JTextField toField = createStyledTextField();
        JTextField dateField = createStyledTextField();
        
        String[] coaches = {"Sleeper", "Normal", "AC Coach", "First Class"};
        JComboBox<String> coachBox = new JComboBox<>(coaches);
        styleComboBox(coachBox);

        JButton bookBtn = new JButton("Book Ticket");
        JButton cancelBtn = new JButton("Cancel Existing Ticket");
        
        stylePrimaryButton(bookBtn);
        styleSecondaryButton(cancelBtn);

        bookBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String from = fromField.getText().trim();
            String to = toField.getText().trim();
            String date = dateField.getText().trim();
            String coach = (String) coachBox.getSelectedItem();

            if (name.isEmpty() || from.isEmpty() || to.isEmpty() || date.isEmpty()) {
                showErrorDialog(this, "Please fill all fields.");
                return;
            }

            String ticket = UUID.randomUUID().toString().split("-")[0].toUpperCase();
            showSuccessDialog(this, "✅ Ticket booked successfully!\n\nTicket ID: " + ticket + "\nCoach: " + coach);
            
            nameField.setText("");
            fromField.setText("");
            toField.setText("");
            dateField.setText("");
        });

        cancelBtn.addActionListener(e -> main.showPanel("Cancellation"));

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));
        card.add(createFieldLabel("Passenger Name"));
        card.add(Box.createVerticalStrut(6));
        card.add(nameField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("From Station"));
        card.add(Box.createVerticalStrut(6));
        card.add(fromField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("To Station"));
        card.add(Box.createVerticalStrut(6));
        card.add(toField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("Travel Date (DD/MM/YYYY)"));
        card.add(Box.createVerticalStrut(6));
        card.add(dateField);
        card.add(Box.createVerticalStrut(12));
        card.add(createFieldLabel("Coach Type"));
        card.add(Box.createVerticalStrut(6));
        card.add(coachBox);
        card.add(Box.createVerticalStrut(20));
        card.add(bookBtn);
        card.add(Box.createVerticalStrut(10));
        card.add(cancelBtn);

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

    private void styleComboBox(JComboBox<String> box) {
        box.setMaximumSize(new Dimension(380, 42));
        box.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
    }

    private void stylePrimaryButton(JButton b) {
        b.setBackground(new Color(0, 150, 136));  // Teal background
        b.setForeground(Color.BLACK);  // BLACK TEXT - ALWAYS VISIBLE
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(240, 44));  // Same width as secondary
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
        b.setForeground(new Color(220, 53, 69));  // Red text
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(true);
        // FIXED: Adjusted width to account for border (1px on each side = 2px total)
        b.setMaximumSize(new Dimension(240, 38));  // Same width as primary
        // FIXED: Reduced inner padding to compensate for border
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 53, 69), 1),
            BorderFactory.createEmptyBorder(7, 17, 7, 17)  // Reduced from (8, 16, 8, 16)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                b.setBackground(new Color(255, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) { 
                b.setBackground(Color.WHITE); 
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
