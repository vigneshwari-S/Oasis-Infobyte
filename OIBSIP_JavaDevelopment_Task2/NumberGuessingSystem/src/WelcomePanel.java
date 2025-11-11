import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WelcomePanel extends JPanel {
    private Main main;
    private int trialAttempts = 0;
    private int trialNumber;
    private JLabel feedback;
    private JTextField trialInput;

    public WelcomePanel(Main main) {
        this.main = main;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));
        add(createCard());
        resetTrial();
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(700, 550));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 3),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        // Game icon
        JLabel icon = new JLabel("🎯", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Number Guessing Game", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112));

        JLabel subtitle = new JLabel("Guess the number between 1-100", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(100, 100, 100));

        JLabel trialLabel = new JLabel("🎮 Try the Game (3 Free Attempts)", SwingConstants.CENTER);
        trialLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        trialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        trialLabel.setForeground(new Color(220, 20, 60));

        trialInput = new JTextField();
        trialInput.setMaximumSize(new Dimension(300, 45));
        trialInput.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        trialInput.setHorizontalAlignment(JTextField.CENTER);
        trialInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        feedback = new JLabel("Enter your guess!", SwingConstants.CENTER);
        feedback.setFont(new Font("Segoe UI", Font.BOLD, 16));
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedback.setForeground(new Color(70, 130, 180));

        JButton guessBtn = new JButton("Guess!");
        JButton signInBtn = new JButton("Sign In");
        JButton signUpBtn = new JButton("Create Account");

        stylePrimaryButton(guessBtn, new Color(100, 149, 237));
        styleSecondaryButton(signInBtn);
        styleSecondaryButton(signUpBtn);

        guessBtn.addActionListener(e -> handleTrialGuess());
        signInBtn.addActionListener(e -> main.showPanel("Login"));
        signUpBtn.addActionListener(e -> main.showPanel("Signup"));

        card.add(icon);
        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));
        card.add(trialLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(trialInput);
        card.add(Box.createVerticalStrut(10));
        card.add(feedback);
        card.add(Box.createVerticalStrut(20));
        card.add(guessBtn);
        card.add(Box.createVerticalStrut(15));
        card.add(signInBtn);
        card.add(Box.createVerticalStrut(10));
        card.add(signUpBtn);

        return card;
    }

    private void handleTrialGuess() {
        if (trialAttempts >= 3) {
            feedback.setText("Trial over! Please sign in to play more.");
            feedback.setForeground(new Color(220, 20, 60));
            return;
        }

        try {
            int guess = Integer.parseInt(trialInput.getText().trim());
            trialAttempts++;

            if (guess == trialNumber) {
                feedback.setText("🎉 Correct! The number was " + trialNumber + "!");
                feedback.setForeground(new Color(34, 139, 34));
                resetTrial();
            } else if (guess < trialNumber) {
                feedback.setText("📈 Too Low! Try again (" + (3 - trialAttempts) + " attempts left)");
                feedback.setForeground(new Color(255, 140, 0));
            } else {
                feedback.setText("📉 Too High! Try again (" + (3 - trialAttempts) + " attempts left)");
                feedback.setForeground(new Color(255, 140, 0));
            }

            if (trialAttempts >= 3 && guess != trialNumber) {
                feedback.setText("❌ Game Over! The number was " + trialNumber + ". Sign in to play more!");
                feedback.setForeground(new Color(220, 20, 60));
            }

            trialInput.setText("");
        } catch (NumberFormatException ex) {
            feedback.setText("Please enter a valid number!");
            feedback.setForeground(new Color(220, 20, 60));
        }
    }

    private void resetTrial() {
        trialAttempts = 0;
        trialNumber = new Random().nextInt(100) + 1;
    }

    private void stylePrimaryButton(JButton b, Color bg) {
        b.setBackground(bg);
        b.setForeground(Color.BLACK);  // BLACK TEXT
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(280, 50));
        b.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(bg);
            }
        });
    }

    private void styleSecondaryButton(JButton b) {
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(100, 149, 237));
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(true);
        b.setMaximumSize(new Dimension(280, 45));
        b.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(new Color(240, 245, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(Color.WHITE);
            }
        });
    }
}
