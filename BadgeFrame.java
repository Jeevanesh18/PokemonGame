
import javax.swing.*;
import java.awt.*;

public class BadgeFrame extends JFrame {
    private static final int FRAME_SIZE = 500;
    private static final int IMAGE_SIZE = 400;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color TEXT_COLOR = Color.WHITE;

    public BadgeFrame(String title, String imageName) {
        setTitle("Displaying " + title + " badge");
        setSize(FRAME_SIZE, FRAME_SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        // Loading and resizing the image
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imageName + ".png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel badgeLabel = new JLabel(resizedIcon, JLabel.CENTER);

        //Setting label
        JLabel titleLabel = new JLabel(title + " Badge Collected", JLabel.CENTER);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(TITLE_FONT);

        // Space
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // 20 pixels top padding

        // Adding label and image to the panel
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(badgeLabel, BorderLayout.CENTER);

        getContentPane().add(panel);
        getContentPane().setBackground(BACKGROUND_COLOR);

        setVisible(true);
    }
}