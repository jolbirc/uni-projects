import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Minesweeper {
    private JPanel rootPanel;
    private JButton restartButton;
    private JLabel minesRemaining;
    private JPanel minePanel;

    private final int width = 7;
    private final int height = 5;
    private ActionListener mineButtonListener;

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }

    public Minesweeper() {
        JFrame frame = new JFrame("Minesweeper!");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();    // fit to size of window
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        GridLayout mineLayout = new GridLayout(height, width);
        minePanel = new JPanel();
        minePanel.setLayout(mineLayout);
        setupMineButtonListener();    // mine button listener must be created before the buttons

        for (int i = 0; i < (width * height); i++) {
            JButton mineButton = new JButton(" ");
            mineButton.addActionListener(mineButtonListener);
            mineButton.setActionCommand(String.valueOf(i));
            minePanel.add(mineButton);
        }
    }

    // instantiate listener
    void setupMineButtonListener() {
        mineButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button " + e.getActionCommand() + " pressed");
                // TODO: determine position of mine in grid and handle
            }
        };
    }

    class Coordinate {
        private int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point otherPoint = (Point) obj;
                return this.x == otherPoint.x && this.y == otherPoint.y;
            }
            return false;
        }
    }

    public class MineInfo {
        private boolean isMine = false;
        private int adjacentMineCount = 0;

        public boolean isMine() {
            return isMine;
        }

        public void setMine(boolean mine) {
            isMine = mine;
        }

        public int getAdjacentMineCount() {
            return adjacentMineCount;
        }

        public void setAdjacentMineCount(int adjacentMineCount) {
            this.adjacentMineCount = adjacentMineCount;
        }
    }

    public class MineField {

        private boolean started = false;
        private int mineCount;
        private int width, height;
        private MineInfo[][] field;

        MineField(int width, int height) {
            field = new MineInfo[height][width];
            this.width = width;
            this.height = height;

            mineCount = (width * height) / 5;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    field[y][x] = new MineInfo();
                }
            }
        }

        public int getMineCount() {
            return mineCount;
        }

        private Coordinate getRandomCoordinate() {
            Random rand = new Random();
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            return new Coordinate(x, y);
        }

        // populates mines avoiding player start location
        private void populateMines(int count, Coordinate startPoint) {
            ArrayList<Coordinate> mineLocations = new ArrayList<>();

            do {
                final Coordinate randomCoordinate = getRandomCoordinate();
                if (!startPoint.equals(randomCoordinate) && !mineLocations.contains(randomCoordinate)) {
                    mineLocations.add(randomCoordinate);
                }
            } while (mineLocations.size() < count);
            for (Coordinate mineLocation : mineLocations) {
                field[mineLocation.getY()][mineLocation.getX()].setMine(true);
            }
        }

        // check each of any given cells neighbours to provide count of adjacent mines
        private Set<Coordinate> validNeighbour(Coordinate coordinate) {
            Set<Coordinate> neighbours = new HashSet<Coordinate>();

            final int minX = Math.max(0, coordinate.getX() - 1);
            final int maxX = Math.min(width - 1, coordinate.getX() + 1);
            final int minY = Math.max(0, coordinate.getY() - 1);
            final int maxY = Math.min(height - 1, coordinate.getY() + 1);

            for (int y = 0; y <= maxY; y++) {
                for (int x = 0; x <= maxX; x++) {
                    neighbours.add(new Coordinate(x, y));
                }
            }

            return neighbours;
        }

        // count total number mines in array
        private int countMinesAtPoints(Set<Coordinate> points) {
            int count = 0;
            for (Coordinate point : points) {
                if (field[point.getY()][point.getX()].isMine()) {
                    count++;
                }
            }
            return count;
        }

        // populate adject mine count of each MineInfo
        private void populateAdjacentMineCount() {
            for (int y = 0; y < field.length; y++) {
                for (int x = 0; x < field[y].length; x++) {
                    final Set<Coordinate> neighbours = validNeighbour(new Coordinate(x, y));
                    field[y][x].setAdjacentMineCount(countMinesAtPoints(neighbours));
                }
            }
        }

        // check mine location with click
    }
}
