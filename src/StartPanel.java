package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {

    public StartPanel(Frame frame) {
        super();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        setSize(frame.getSize());

        JLabel title = Frame.addTextToComp("Iowa State University", new Font(Font.SANS_SERIF, Font.BOLD, 50),this);
        title.setForeground(new Color(250, 180, 0));

        BufferedImage imageResource;
        try {
            imageResource = ImageIO.read(new File("CSEClubLogo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel image = new JLabel(new ImageIcon(imageResource));
        image.setAlignmentX(CENTER_ALIGNMENT);
        add(image);
        setAlignmentX(CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start game");
        startButton.setVerticalTextPosition(AbstractButton.CENTER);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setAlignmentY(BOTTOM_ALIGNMENT);
        startButton.addActionListener((l) -> frame.startMazeGame());
        startButton.setForeground(Color.white);
        startButton.setBackground(new Color(140, 0, 0));
        startButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        startButton.setFocusPainted(false);
        startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        startButton.setForeground(new Color(255, 255, 200));
        this.add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {

    }
}
