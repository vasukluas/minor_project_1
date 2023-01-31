import javax.swing.*;
import java.awt.*; //here we are using awt for to perform the GUI actions
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//here we are going to create a class for creating a window
public class tictactoe implements ActionListener {
    JFrame window = new JFrame("Tic-Tac-Toe"); // here we creating a window and name it as tic tac toe
    // we are creating the panels
    JPanel titlepanel = new JPanel();
    JPanel buttonpanel = new JPanel();
    JPanel reloadpanel = new JPanel();
    JPanel scorepanel = new JPanel(); // in this game we have 4 parts so we giving the 4 parts as panels space

    JLabel textfield = new JLabel();
    JLabel scoreLabelPlayerX = new JLabel(); // giving the naming
    JLabel scoreLabelPlayerO = new JLabel();

    JButton[] buttons = new JButton[9];
    JButton reloadButton = new JButton("reload"); // giving some panels we have give the buttons
    JButton closeButton = new JButton("close");

    int playerXscore = 0;
    int playerOscore = 0;

    boolean playerXTurn;

    tictactoe() {
        window.setSize(1000, 800);
        window.getContentPane().setBackground(new Color(50, 50, 50));
        window.setLayout(new BorderLayout());
        window.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        titlepanel.setLayout(new BorderLayout());
        titlepanel.setBounds(0, 0, 1000, 100);
        titlepanel.add(textfield); // we are adding the textfield to the titlepanel

        buttonpanel.setLayout(new GridLayout(3, 3));
        buttonpanel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonpanel.add(buttons[i]); // here we adding the 9 buttons to the button panel
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        reloadpanel.setLayout(new GridLayout(1, 2));
        reloadpanel.setBackground(new Color(150, 150, 150));

        reloadButton.setBackground(new Color(0, 0, 255));
        reloadButton.setFocusable(false);
        reloadButton.addActionListener(this);

        closeButton.setBackground(new Color(255, 0, 0));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        reloadpanel.add(reloadButton);
        reloadpanel.add(closeButton);

        scorepanel.setLayout(new GridLayout(2, 1));
        scorepanel.setBackground(new Color(255, 255, 0));

        scoreLabelPlayerX.setFont(new Font("MV Boli", Font.BOLD, 20));
        scoreLabelPlayerX.setText("Player X:" + playerXscore);

        scoreLabelPlayerO.setFont(new Font("MV Boli", Font.BOLD, 20));
        scoreLabelPlayerO.setText("Player O:" + playerOscore);

        scorepanel.add(scoreLabelPlayerX);
        scorepanel.add(scoreLabelPlayerO);

        window.add(titlepanel, BorderLayout.NORTH);
        window.add(buttonpanel);
        window.add(reloadpanel, BorderLayout.SOUTH);
        window.add(scorepanel, BorderLayout.EAST);

        firstturn();
    }

    public void firstturn() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.5) {
            playerXTurn = true;
            textfield.setText("X Turn");
        } else {
            playerXTurn = false;
            textfield.setText("O Turn");
        }
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerXTurn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 255, 0));
                        buttons[i].setText("X");
                        playerXTurn = false;
                        textfield.setText("O Turn");
                        textfield.setForeground(new Color(0, 0, 255));
                        check();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        playerXTurn = true;
                        textfield.setText("X Turn");
                        textfield.setForeground(new Color(0, 255, 0));
                        check();
                    }
                }
            }
        }
        if (e.getSource() == reloadButton) {
            reload();
        }
        if (e.getSource() == closeButton) {
            window.dispose();
        }
    }

    public void check() {
        // --------------horizontal conditions-----------------
        if (buttons[0].getText() == "X" &&
                buttons[1].getText() == "X" &&
                buttons[2].getText() == "X") {
            xWins(0, 1, 2);
        }
        if (buttons[3].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[5].getText() == "X") {
            xWins(3, 4, 5);
        }
        if (buttons[6].getText() == "X" &&
                buttons[7].getText() == "X" &&
                buttons[8].getText() == "X") {
            xWins(6, 7, 8);
        }
        // --------------vertical conditions-----------------
        if (buttons[0].getText() == "X" &&
                buttons[3].getText() == "X" &&
                buttons[6].getText() == "X") {
            xWins(0, 3, 6);
        }
        if (buttons[1].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[7].getText() == "X") {
            xWins(1, 4, 7);
        }
        if (buttons[2].getText() == "X" &&
                buttons[5].getText() == "X" &&
                buttons[8].getText() == "X") {
            xWins(2, 5, 8);
        }
        // --------------diagonal conditions-----------------
        if (buttons[0].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[8].getText() == "X") {
            xWins(0, 4, 8);
        }
        if (buttons[2].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[6].getText() == "X") {
            xWins(2, 4, 6);
        }

        // --------------horizontal conditions-----------------
        if (buttons[0].getText() == "O" &&
                buttons[1].getText() == "O" &&
                buttons[2].getText() == "O") {
            oWins(0, 1, 2);
        }
        if (buttons[3].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[5].getText() == "O") {
            oWins(3, 4, 5);
        }
        if (buttons[6].getText() == "O" &&
                buttons[7].getText() == "O" &&
                buttons[8].getText() == "O") {
            oWins(6, 7, 8);
        }
        // --------------vertical conditions-----------------
        if (buttons[0].getText() == "O" &&
                buttons[3].getText() == "O" &&
                buttons[6].getText() == "O") {
            oWins(0, 3, 6);
        }
        if (buttons[1].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[7].getText() == "O") {
            oWins(1, 4, 7);
        }
        if (buttons[2].getText() == "O" &&
                buttons[5].getText() == "O" &&
                buttons[8].getText() == "O") {
            oWins(2, 5, 8);
        }
        // --------------diagonal conditions-----------------
        if (buttons[0].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[8].getText() == "O") {
            oWins(0, 4, 8);
        }
        if (buttons[2].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[6].getText() == "O") {
            oWins(2, 4, 6);
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
        playerXscore++;
        scoreLabelPlayerX.setText("player x:" + playerXscore);
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
        playerOscore++;
        scoreLabelPlayerX.setText("player o:" + playerOscore);
    }

    public void reload() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(new Color(240, 240, 240));
            buttons[i].setEnabled(true);
        }
        firstturn();
    }
}