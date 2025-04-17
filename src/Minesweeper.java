import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Minesweeper
{
    private JPanel rootPanel;
    private JButton restartButton;
    private JLabel minesRemaining;
    private JPanel minePanel;

    private final int width = 7;
    private final int height = 5;
    private ActionListener mineButtonListener;

    public static void main(String[] args)
    {
        Minesweeper minesweeper = new Minesweeper();
    }

    public Minesweeper()
    {
        JFrame frame = new JFrame("Minesweeper!");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();    // fit to size of window
        frame.setVisible(true);
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
        GridLayout mineLayout = new GridLayout(height, width);
        minePanel = new JPanel();
        minePanel.setLayout(mineLayout);
        setupMineButtonListener();

        for (int i = 0; i < (width * height); i++)
        {
            JButton mineButton = new JButton(" ");
            mineButton.addActionListener(mineButtonListener);
            mineButton.setActionCommand(String.valueOf(i));
        }
    }

    // instantiate listener
    void setupMineButtonListener()
    {
        mineButtonListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button " + e.getActionCommand() + " pressed");
                // TODO: determine position of mine in grid and handle

            }
        };
    }
}
