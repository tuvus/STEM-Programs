package Programs.ProgramTemplate;

import StartMenu.Frame;
import StartMenu.Program;
import Utils.RoundButton;
import Utils.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProgramTemplatePanel extends JPanel {
    private final Frame frame;
    private final ProgramTemplateKeyInputListener inputListener;

    public ProgramTemplatePanel(Frame frame, Program program) {
        this.frame = frame;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        add(Box.createVerticalStrut(10));

        //Title
        UIUtils.addTitle("Program Template", this);

        add(Box.createVerticalStrut(10));

        //Button
        JButton button = new RoundButton("Back", Color.WHITE, 20, Color.RED, 10);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.addActionListener((e) -> program.endProgram());
        add(button);

        add(Box.createVerticalStrut(10));

        //Input Field
        JTextArea inputField = UIUtils.addTextInputField("Input text here.", 20, this);
        inputField.setSize(new Dimension(100, 30));
        inputField.setMaximumSize(new Dimension(500, 300));
        inputField.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        //Keyboard Input Listener
        inputListener = new ProgramTemplateKeyInputListener(frame, program, this, inputField);
    }


}
