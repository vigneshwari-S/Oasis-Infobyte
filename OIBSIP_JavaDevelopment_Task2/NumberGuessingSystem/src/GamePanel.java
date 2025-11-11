import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private Main main;
    private int target;
    private int attempts;
    private int score;
    private int minRange = 1;   // Track minimum possible value
    private int maxRange = 100; // Track maximum possible value
    private JLabel feedback, attemptsLabel, scoreLabel, rangeHint;
    private JTextField guessField;

    public GamePanel(Main main) {
        this.main = main;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));
        add(createCard());
        startNewRound();
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(750, 620));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0), 3),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel icon = new JLabel("🎯", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Number Guessing Game", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112));

        JLabel subtitle = new JLabel("Guess the number between 1-100", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(100, 100, 100));

        // Stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        statsPanel.setOpaque(false);
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        attemptsLabel.setForeground(new Color(255, 140, 0));

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(34, 139, 34));

        statsPanel.add(attemptsLabel);
        statsPanel.add(scoreLabel);

        // Range hint label - NEW!
        rangeHint = new JLabel("💡 Hint: Try between 1-100", SwingConstants.CENTER);
        rangeHint.setFont(new Font("Segoe UI", Font.BOLD, 15));
        rangeHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        rangeHint.setForeground(new Color(138, 43, 226)); // Purple

        guessField = new JTextField();
        guessField.setMaximumSize(new Dimension(300, 50));
        guessField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        feedback = new JLabel("Make your guess!", SwingConstants.CENTER);
        feedback.setFont(new Font("Segoe UI", Font.BOLD, 18));
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedback.setForeground(new Color(70, 130, 180));

        JButton guessBtn = new JButton("🎲 Guess!");
        JButton newGameBtn = new JButton("🔄 New Game");
        JButton scoreboardBtn = new JButton("🏆 View Scoreboard");
        JButton logoutBtn = new JButton("Logout");

        stylePrimaryButton(guessBtn, new Color(255, 140, 0));
        stylePrimaryButton(newGameBtn, new Color(100, 149, 237));
        stylePrimaryButton(scoreboardBtn, new Color(147, 112, 219));
        styleSecondaryButton(logoutBtn);

        guessBtn.addActionListener(e -> handleGuess());
        newGameBtn.addActionListener(e -> startNewRound());
        scoreboardBtn.addActionListener(e -> main.showPanel("Scoreboard"));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                main.logout();
            }
        });

        card.add(icon);
        card.add(Box.createVerticalStrut(10));
        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(20));
        card.add(statsPanel);
        card.add(Box.createVerticalStrut(15));
        card.add(rangeHint);  // NEW: Show range hint
        card.add(Box.createVerticalStrut(15));
        card.add(guessField);
        card.add(Box.createVerticalStrut(15));
        card.add(feedback);
        card.add(Box.createVerticalStrut(25));
        card.add(guessBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(newGameBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(scoreboardBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(logoutBtn);

        return card;
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess == target) {
                int earnedScore = Math.max(0, 100 - (attempts * 10));
                score += earnedScore;
                scoreLabel.setText("Score: " + score);
                main.saveScore(earnedScore);

                feedback.setText("🎉 Correct! You earned " + earnedScore + " points!");
                feedback.setForeground(new Color(34, 139, 34));
                rangeHint.setText("🎉 Winner! Starting new round...");
                rangeHint.setForeground(new Color(34, 139, 34));

                JOptionPane.showMessageDialog(this, 
                    "Congratulations! You guessed it in " + attempts + " attempts!\nYou earned " + earnedScore + " points!",
                    "Winner!", JOptionPane.INFORMATION_MESSAGE);

                startNewRound();
            } else if (guess < target) {
                // Update minimum range
                minRange = Math.max(minRange, guess + 1);
                
                feedback.setText("📈 Too Low! Try higher.");
                feedback.setForeground(new Color(255, 69, 0));
                
                // Show smart hint with narrowed range
                rangeHint.setText("💡 Hint: Try between " + minRange + "-" + maxRange);
                rangeHint.setForeground(new Color(138, 43, 226));
            } else {
                // Update maximum range
                maxRange = Math.min(maxRange, guess - 1);
                
                feedback.setText("📉 Too High! Try lower.");
                feedback.setForeground(new Color(220, 20, 60));
                
                // Show smart hint with narrowed range
                rangeHint.setText("💡 Hint: Try between " + minRange + "-" + maxRange);
                rangeHint.setForeground(new Color(138, 43, 226));
            }

            guessField.setText("");
        } catch (NumberFormatException ex) {
            feedback.setText("❌ Please enter a valid number!");
            feedback.setForeground(new Color(220, 20, 60));
            rangeHint.setText("💡 Hint: Try between " + minRange + "-" + maxRange);
        }
    }

    private void startNewRound() {
        target = new Random().nextInt(100) + 1;
        attempts = 0;
        minRange = 1;    // Reset range
        maxRange = 100;  // Reset range
        attemptsLabel.setText("Attempts: 0");
        feedback.setText("New game started! Make your guess!");
        feedback.setForeground(new Color(70, 130, 180));
        rangeHint.setText("💡 Hint: Try between 1-100");
        rangeHint.setForeground(new Color(138, 43, 226));
        guessField.setText("");
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
        b.setForeground(new Color(220, 20, 60));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setMaximumSize(new Dimension(280, 40));
        b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
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
}
