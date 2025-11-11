import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.*;

public class ScoreboardPanel extends JPanel {
    private Main main;

    public ScoreboardPanel(Main main) {
        this.main = main;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 255));
        add(createCard());
    }

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(700, 550));
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 215, 0), 3),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel icon = new JLabel("🏆", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Leaderboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(25, 25, 112));

        JLabel subtitle = new JLabel("Top Players by Total Score", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setForeground(new Color(100, 100, 100));

        headerPanel.add(icon);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);

        // Scoreboard
        Map<String, Integer> map = main.loadAggregateScores();
        LinkedHashMap<String, Integer> sorted = map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));

        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setBackground(Color.WHITE);

        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            if (rank > 10) break;

            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
            rowPanel.setOpaque(false);
            rowPanel.setMaximumSize(new Dimension(600, 50));

            String medal = "";
            Color rankColor = new Color(100, 100, 100);
            if (rank == 1) { medal = "🥇"; rankColor = new Color(255, 215, 0); }
            else if (rank == 2) { medal = "🥈"; rankColor = new Color(192, 192, 192); }
            else if (rank == 3) { medal = "🥉"; rankColor = new Color(205, 127, 50); }

            JLabel rankLabel = new JLabel(medal + " #" + rank);
            rankLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            rankLabel.setForeground(rankColor);
            rankLabel.setPreferredSize(new Dimension(80, 30));

            JLabel nameLabel = new JLabel(entry.getKey());
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nameLabel.setForeground(new Color(50, 50, 50));
            nameLabel.setPreferredSize(new Dimension(250, 30));

            JLabel scoreLabel = new JLabel(entry.getValue() + " pts");
            scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            scoreLabel.setForeground(new Color(34, 139, 34));
            scoreLabel.setPreferredSize(new Dimension(100, 30));

            rowPanel.add(rankLabel);
            rowPanel.add(nameLabel);
            rowPanel.add(scoreLabel);

            scoresPanel.add(rowPanel);
            rank++;
        }

        if (sorted.isEmpty()) {
            JLabel emptyLabel = new JLabel("No scores yet. Play to add your score!", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(new Color(150, 150, 150));
            scoresPanel.add(Box.createVerticalStrut(20));
            scoresPanel.add(emptyLabel);
        }

        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);

        JButton backBtn = new JButton("🎮 Back to Game");
        JButton logoutBtn = new JButton("Logout");

        // FIXED: Both buttons now have solid, visible backgrounds
        stylePrimaryButton(backBtn, new Color(100, 149, 237));  // Blue
        styleDangerButton(logoutBtn, new Color(220, 20, 60));    // Red

        backBtn.addActionListener(e -> main.showPanel("Game"));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                main.logout();
            }
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(logoutBtn);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void stylePrimaryButton(JButton b, Color bg) {
        b.setBackground(bg);
        b.setForeground(Color.BLACK);  // BLACK TEXT - ALWAYS VISIBLE
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(180, 50));
        b.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(bg);
            }
        });
    }

    private void styleDangerButton(JButton b, Color bg) {
        b.setBackground(bg);
        b.setForeground(Color.WHITE);  // WHITE TEXT ON RED
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(140, 50));
        b.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(bg);
            }
        });
    }
}
