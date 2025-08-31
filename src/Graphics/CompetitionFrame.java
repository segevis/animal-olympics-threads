//segev isaac 207938085
//shifra avigdor 207067125


package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CompetitionFrame extends JFrame {

    private static CompetitionPanel competitionPanel;

    public CompetitionFrame() {

        super("Competition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(fileMenu);
        fileMenu.add(exitMenuItem);
        this.setJMenuBar(menuBar);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem HelpMenuItem = new JMenuItem("Help");
        HelpMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Homework 2" + "\n Gui");
            }
        });
        helpMenu.add(HelpMenuItem);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        setLocationRelativeTo(null);

        competitionPanel = new CompetitionPanel();
        getContentPane().add(competitionPanel, BorderLayout.CENTER);

        validate();
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                new CompetitionFrame();
            }
        });
    }
}
