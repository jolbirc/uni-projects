import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperUI
{
    private JPanel panel1;
    private JButton restartButton;
    private JLabel minesRemaining;
    private JPanel minePanel;

    private final int width = 7;
    private final int height = 5;
    private ActionListener mineButtonListener;


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
