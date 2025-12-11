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
    private ActionListener mineButtonListener;

    private static final int width = 7;
    private static final int height = 5;
    private MineField mineField;



    public static void main(String[] args) {
        new Minesweeper();
    }

    public Minesweeper() {
        mineField = new MineField(width, height);

        JFrame frame = new JFrame("Minesweeper!");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();    // fit to size of window
        frame.setVisible(true);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetButton();
            }
        });


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
                // TODO: determine position of mine in grid and
                int mineId = Integer.parseInt(e.getActionCommand());
                Coordinate coordinate = mineField.pointForId(mineId);
                MineInfo info = mineField.checkMine(coordinate);
                JButton button = (JButton) e.getSource();
                if (info.isMine()) {
                    explodeAllMines();
                } else {
                    button.setText(String.valueOf(info.getAdjacentMineCount()));
                }
            }
        };
    }

    private void resetButton() {
        mineField = new MineField(width, height);
        for (Component possibleButton : minePanel.getComponents()) {
            if (possibleButton instanceof JButton) {
                JButton actualButton = (JButton)possibleButton;
                actualButton.setText(" ");
            }
        }
    }
    
    static class Coordinate {
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
            if (obj instanceof Coordinate) {
                Coordinate otherPoint = (Coordinate) obj;
                return this.x == otherPoint.x && this.y == otherPoint.y;
            }
            return false;
        }
    }

    public static class MineInfo {
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

    // iterate through all jbuttons and change to asterisk if mine
    private void explodeAllMines() {
        for (Component component : minePanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                int mineId = Integer.parseInt(button.getActionCommand());
                Coordinate coordinate = mineField.pointForId(mineId);
                MineInfo info = mineField.checkMine(coordinate);
                if (info.isMine()) {
                    button.setText("*");
                }
            }
        }
    }

    public static class MineField {

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

        MineInfo checkMine(Coordinate coordinate) {
            if (!started) {
                started = true;
                populateMines(mineCount, coordinate);
                populateAdjacentMineCount();
            }
            MineInfo location = field[coordinate.getY()][coordinate.getX()];
            MineInfo mineInfo = new MineInfo();
            mineInfo.setAdjacentMineCount(location.getAdjacentMineCount());
            mineInfo.setMine(location.isMine());
            return mineInfo;
        }

        // check mine location with click
        public Coordinate pointForId(int mineId) {
            int x = mineId % width;
            int y = mineId / width;
            return new Coordinate(x, y);
        }


    }
}
