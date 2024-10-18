package StartMenu;


import Utils.RoundPanel;
import Utils.UIUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramListPanel extends JPanel {

    JScrollPane programList;
    JPanel programListContainer;

    public ProgramListPanel(Frame frame) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(frame.getSize());
        setBackground(Color.darkGray);

        JLabel title = UIUtils.addTitle("Program List", new Font(Font.SANS_SERIF, Font.BOLD, 30), this);
        title.setForeground(Color.white);
        add(Box.createVerticalStrut(20));

        // Create a JPanel to hold a list of labels.
        programListContainer = new RoundPanel(Color.LIGHT_GRAY, 50);
        programListContainer.setLayout(new BoxLayout(programListContainer, BoxLayout.Y_AXIS));
        // Add a large number of labels to the panel.
        for (Program newProgram : getPrograms()) {
            JButton newProgramPanel = new JButton();
            newProgramPanel.setLayout(new BoxLayout(newProgramPanel, BoxLayout.Y_AXIS));

            newProgramPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            newProgramPanel.addActionListener((l) -> frame.startProgram(newProgram));
            newProgramPanel.setAlignmentX(CENTER_ALIGNMENT);

            //Add the text
            UIUtils.addTitle(newProgram.getProgramName(), newProgramPanel);
            JTextArea text = UIUtils.addTextArea(newProgram.getProgramDescription(), 20, null);
            JScrollPane scrollPane = new JScrollPane(text);
            newProgramPanel.add(scrollPane);

            newProgramPanel.setMaximumSize(new Dimension(600, 180));
            programListContainer.add(newProgramPanel);
            programListContainer.add(Box.createVerticalStrut(10));
        }

        // Create a JScrollPane and set the panel as its viewport.
        programList = new JScrollPane(programListContainer);
        programList.setMaximumSize(new Dimension(800, 1000));
        programList.setOpaque(false);
        programList.getViewport().setOpaque(false);
        programList.setBorder(new EmptyBorder(0,0,0,0));
        programList.getVerticalScrollBar().setUnitIncrement(20);
        // Add the JScrollPane to the frame.
        this.add(programList);
        frame.setVisible(true);

    }

    private static List<Program> getPrograms() {
        ArrayList<Program> programs = new ArrayList<>();

        File file = new File("src/main/java/Programs");
        try {
            List<String> programNames = Arrays.stream(file.listFiles()).filter(f -> f.isDirectory()).map(d -> d.getName()).toList();
            programNames.forEach(n -> {
                try {
                    programs.add((Program) ClassLoader.getSystemClassLoader().loadClass("Programs." + n + "." + n).getMethod("programFactory").invoke(null));
                } catch (ClassNotFoundException e) {
                    System.err.println("Failed to load program: " + n + ". It is missing a class of the name " + n + " that can be used to create a program.");
                } catch (NoSuchMethodException e) {
                    System.err.println("Failed to find corresponding factory function in: " + n);
                } catch (InvocationTargetException e) {
                    System.err.println("The static method programFactory() in the program " + n + " does not exist.");
                } catch (IllegalAccessException e) {
                    System.err.println("The static method programFactory() in the program " + n + " was not public.");
                } catch (NullPointerException e) {
                    System.err.println("The static method programFactory() in the program " + n + " was not static.");
                } catch (ClassCastException e) {
                    System.err.println("The static method programFactory() in the program " + n + " returned an object that does not extend from Program.");
                }
            });
        } catch (Exception e) {
            System.out.println("Failed to load programs!");
        }
        return programs;
    }

    static Program createStartMenuProgram() {
        return new Program() {
            @Override
            public JPanel startProgram(Frame frame) {
                return new ProgramListPanel(frame);
            }

            @Override
            public String getProgramName() {
                return "Start Menu";
            }

            @Override
            public String getProgramDescription() {
                return null;
            }
        };
    }
}
