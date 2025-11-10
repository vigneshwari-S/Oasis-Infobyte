import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class ReservationPanel extends JPanel {
    public ReservationPanel(Main main) {
        setLayout(new GridBagLayout());
        setBackground(new Color(237, 255, 250));
        add(createCard(main));
    }

    private JPanel createCard(Main main) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(600,420));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220)),
            BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        JLabel title = new JLabel("Make a Reservation", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(0,120,80));

        JTextField nameField = new JTextField();
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField dateField = new JTextField();
        String[] coaches = {"Sleeper", "Normal"};
        JComboBox<String> coachBox = new JComboBox<>(coaches);

        Dimension d = new Dimension(350,36);
        nameField.setMaximumSize(d);
        fromField.setMaximumSize(d);
        toField.setMaximumSize(d);
        dateField.setMaximumSize(d);
        coachBox.setMaximumSize(d);

        JButton bookBtn = new JButton("Book Ticket");
        JButton cancelBtn = new JButton("Cancel Ticket");
        styleButton(bookBtn);
        styleOutline(cancelBtn);

        bookBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String from = fromField.getText().trim();
            String to = toField.getText().trim();
            String date = dateField.getText().trim();
            String coach = (String) coachBox.getSelectedItem();
            if (name.isEmpty() || from.isEmpty() || to.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            // generate a simple ticket id
            String ticket = UUID.randomUUID().toString().split("-")[0].toUpperCase();
            JOptionPane.showMessageDialog(this, "✅ Ticket booked successfully!\nTicket ID: " + ticket);
            // clear fields
            nameField.setText("");
            fromField.setText("");
            toField.setText("");
            dateField.setText("");
        });

        cancelBtn.addActionListener(e -> main.showPanel("Cancellation"));

        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(new JLabel("Passenger Name")); card.add(nameField);
        card.add(Box.createVerticalStrut(8));
        card.add(new JLabel("From")); card.add(fromField);
        card.add(Box.createVerticalStrut(8));
        card.add(new JLabel("To")); card.add(toField);
        card.add(Box.createVerticalStrut(8));
        card.add(new JLabel("Date")); card.add(dateField);
        card.add(Box.createVerticalStrut(8));
        card.add(new JLabel("Coach Type")); card.add(coachBox);
        card.add(Box.createVerticalStrut(14));
        card.add(bookBtn);
        card.add(Box.createVerticalStrut(8));
        card.add(cancelBtn);

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
