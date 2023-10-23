package src.MazeGame;

import javax.swing.*;
import java.awt.*;

public class CellUI extends JPanel {
    Cell cell;
    int size;

    public CellUI(Cell cell, int size) {
        setLayout(null);
        this.cell = cell;

        setSize(size, size);
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cell.isStartCell())
            setBackground(Color.green);
        else if (cell.isEndCell())
            setBackground(Color.red);
        else
            setBackground(Color.lightGray);
        g.setColor(Color.BLACK);

        if (cell.walls[0])
            g.fillRect(0, 0, 3, getHeight()); //left
        else
            g.fillRect(0, 0, 1, getHeight()); //left
        if (cell.walls[1])
            g.fillRect(0, 0, getWidth(), 3); //top
        else
            g.fillRect(0, 0, getWidth(), 1); //top
        if (cell.walls[2])
            g.fillRect(0, getHeight() - 2, getWidth(), 3); //bottom
        else
            g.fillRect(0, getHeight(), getWidth(), 1); //bottom
        if (cell.walls[3])
            g.fillRect(getWidth() - 2, 0, 3, getHeight()); //right
        else
            g.fillRect(getWidth(), 0, 3, getHeight()); //right
        g.setColor(Color.LIGHT_GRAY);
    }
}
