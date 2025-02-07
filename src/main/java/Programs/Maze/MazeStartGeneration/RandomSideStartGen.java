package Programs.Maze.MazeStartGeneration;

import Programs.Maze.Cell;

import java.util.Random;

class RandomSideStartGen implements MazeStartGenAlgorithm {
    @Override
    public Cell generateMazeStart(Cell[][] grid) {
        Random rand = new Random();
        int side = rand.nextInt(4);
        Cell startCell;
        if (side == 0)
            startCell = grid[0][rand.nextInt(grid[0].length)];
        else if (side == 1)
            startCell = grid[grid.length - 1][rand.nextInt(grid[0].length)];
        else if (side == 2)
            startCell = grid[rand.nextInt(grid.length)][0];
        else
            startCell = grid[rand.nextInt(grid.length)][grid[0].length - 1];
        return startCell;
    }
}
